package yongs.temp.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import yongs.temp.db.Employee;
import yongs.temp.db.EmployeeDao;
import yongs.temp.rabbitmq.ActiveStandbyObserver;
  
@Component
public class EmployeeScheduler {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeScheduler.class);
	
    @Autowired
    private EmployeeDao employeeDao;
     
	@Scheduled(cron = "0 * * * * *")
	public void onCreateJob() {
		if (ActiveStandbyObserver.RUNNING_STATUS) {			
			///////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////
			
			logger.info(" >>> >>> >>> >>> >>> START Employee business logic ...");
			// business logic here
			String gender;
			
			for (int idx=0; idx<2; idx++) {
				if(idx%2==0) gender = "F";
				else gender = "M";
				// DB 호출
				Employee employee = new Employee("BATCH"+idx, gender, "100001", "1", "1", "010-0000-0000", "20000101", "140200", "LGCNS DEVON BATCH"); 
				employeeDao.createEmployee(employee);
			}	 		
			logger.info(" >>> >>> >>> >>> >>> END Employee business logic ...");
			
			///////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////
		} 
	}
}