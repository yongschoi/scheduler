package yongs.temp.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActiveStandbyObserver {
	private static final Logger logger = LoggerFactory.getLogger(ActiveStandbyObserver.class);
	@Value("${yongs.temp.scheduler}")
	private String scheduler;
	  
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    // 상대방서버 응답 대기시간(default 30초)
	private static final int WAIT_TIME = 30; 
	
	private static Long count = 0L;
	private static Long otherTime = System.currentTimeMillis();

	public static Boolean RUNNING_STATUS = false;

	public void onSignal(Long time) {
		ActiveStandbyObserver.count++;
		ActiveStandbyObserver.otherTime = time;
    } 
	
	@Scheduled(cron = "0/5 * * * * *")
	public void monitor() {
		Long diff = System.currentTimeMillis() - otherTime;
		logger.debug("<count> " + ActiveStandbyObserver.count.toString());
		logger.debug("<diff> " + diff.toString());
				
		// 상대방 응답이 없고(count == 0) && WAIT_TIME(초)동안 응답이 없으면, 상대방 서버 down
		if(ActiveStandbyObserver.count == 0 && diff > WAIT_TIME * 1000)
			ActiveStandbyObserver.RUNNING_STATUS = true;
		
		// 상대방 queue가 쌓이면(상대방이 기동중이라는 의미)
		if(ActiveStandbyObserver.count > 5)
			ActiveStandbyObserver.RUNNING_STATUS = false;
		
		if(ActiveStandbyObserver.RUNNING_STATUS)
			logger.info("<Running> " + scheduler);
		else
			logger.info("<Not Running> " + scheduler);
		
		// count refresh
		ActiveStandbyObserver.count = 0L;
	}
		
	@Scheduled(cron = "0/3 * * * * *")
	public void toSignal() {
		try {		
			rabbitTemplate.convertAndSend(scheduler, System.currentTimeMillis());
		} catch (Exception e) {
			e.getStackTrace();
			logger.error("RabbitMQ Server Error!");
		}		
	}
}