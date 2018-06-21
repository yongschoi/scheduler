package yongs.temp.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GreetingDao extends JdbcDaoSupport {    
    @Autowired
    public GreetingDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public void createGreeting(Greeting greeting) {
    	// seq 는 AUTO_INCREMENT 설정 
        String create_sql = "INSERT INTO greeting (name) VALUES (?)";
               
        Object[] params = new Object[] { greeting.getName() };
        this.getJdbcTemplate().update(create_sql, params);
    } 
}
