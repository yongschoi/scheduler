package yongs.temp.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import yongs.temp.db.Greeting;
import yongs.temp.db.GreetingDao;
import yongs.temp.rabbitmq.ActiveStandbyObserver;

@Component
public class GreetingScheduler {
	private static final Logger logger = LoggerFactory.getLogger(GreetingScheduler.class);
	
    @Autowired
    private GreetingDao greetingDao;
     
	@Scheduled(cron = "0/30 0/5 * * * *")
	public void onCreateJob() {
		if (ActiveStandbyObserver.RUNNING_STATUS) {			
			////////////////////////////////////////////////////////////////////
			////////////////////     BUSINESS LOGIC CODE    ////////////////////
			////////////////////////////////////////////////////////////////////

			logger.info(" >>> >>> >>> >>> >>> START Greeting business logic ...");
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("MM/dd hh:mm:ss");
			String createTime = dayTime.format(new Date(time));
			
			// DB 호출
			greetingDao.createGreeting(new Greeting("Hong [" + createTime + "] created."));			
			logger.info(" >>> >>> >>> >>> >>> END Greeting business logic ...");
			
			////////////////////////////////////////////////////////////////////
			////////////////////     BUSINESS LOGIC CODE    ////////////////////
			////////////////////////////////////////////////////////////////////
		} 
	}
}