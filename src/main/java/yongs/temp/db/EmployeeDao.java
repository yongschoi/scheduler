package yongs.temp.db;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao extends JdbcDaoSupport {    
    @Autowired
    public EmployeeDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public void createEmployee(Employee employee) {
    	// seq 는 AUTO_INCREMENT 설정 
        String create_sql = "INSERT INTO employee (name, sex, department_code, joblevel_code, skill_code, telephone, birthdate, postal, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
               
        Object[] params = new Object[] { employee.getName(), employee.getSex(), employee.getDepartmentCode(), employee.getJoblevelCode(), employee.getSkillCode(), employee.getTelephone(), employee.getBirthdate(), employee.getPostal(), employee.getAddress()};
        this.getJdbcTemplate().update(create_sql, params);
    }
}
