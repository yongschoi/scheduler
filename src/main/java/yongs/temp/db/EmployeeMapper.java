package yongs.temp.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {
    public static final String BASE_SQL = "SELECT seq, name, sex, department_code, joblevel_code, skill_code, telephone, birthdate, postal, address FROM employee";
 
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
    	int seq = rs.getInt("seq");
        String name = rs.getString("name");
        String sex = rs.getString("sex");
        String department_code = rs.getString("department_code");
        String joblevel_code = rs.getString("joblevel_code");
        String skill_code = rs.getString("skill_code");
        String telephone = rs.getString("telephone");
        String birthdate = rs.getString("birthdate");
        String postal = rs.getString("postal");
        String address = rs.getString("address");
 
        return new Employee(seq, name, sex, department_code, joblevel_code, skill_code, telephone, birthdate, postal, address);
    }  
}