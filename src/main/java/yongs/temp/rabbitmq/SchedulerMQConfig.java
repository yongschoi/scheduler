package yongs.temp.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
public class SchedulerMQConfig {
	private static final int RabbitMQ_SERVER_PORT = 5672;
	private static final String RabbitMQ_SERVER_USER = "guest";
	private static final String RabbitMQ_SERVER_PASSWD = "guest";
			
	@Value("${yongs.temp.rabbitmq.server.ip}")
	private String RabbitMQ_SERVER_IP;
	
	@Value("${yongs.temp.scheduler}")
	private String scheduler;
	
	static final String directExchangeName = "yongs.direct.exchange";

	@Bean
	@Primary 
	Queue activeQueue() {
		return new Queue("active", false);
	}

	@Bean
	Queue standbyQueue() {
		return new Queue("standby", false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(directExchangeName);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(scheduler);
	}

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setRoutingKey(scheduler);
        return template;
    } 

    @Bean
	SimpleMessageListenerContainer container(MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		// 초기 startup시 active인지 standby 인지 알아야 함.(Listener는 상대방 Queue를 셋팅함)
		if(scheduler.equals("active")) { 
			container.setQueueNames("standby");  
			ActiveStandbyObserver.RUNNING_STATUS = true;
		} else {
			container.setQueueNames("active");
			ActiveStandbyObserver.RUNNING_STATUS = false;
		}
	 	
		container.setMessageListener(listenerAdapter);
		return container;
	}

    @Bean
    MessageListenerAdapter listenerAdapter(ActiveStandbyObserver activeStandbyObserver) {
        return new MessageListenerAdapter(activeStandbyObserver, "onSignal");
    }
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(RabbitMQ_SERVER_IP);
        factory.setPort(RabbitMQ_SERVER_PORT);
        factory.setUsername(RabbitMQ_SERVER_USER);
        factory.setPassword(RabbitMQ_SERVER_PASSWD);
        
        return factory;
    }	
    
    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }
}