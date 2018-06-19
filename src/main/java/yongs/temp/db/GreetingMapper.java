package yongs.temp.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GreetingMapper implements RowMapper<Greeting> {
    public static final String BASE_SQL = "SELECT seq, name FROM greeting";
 
    public Greeting mapRow(ResultSet rs, int rowNum) throws SQLException {
    	int seq = rs.getInt("seq");
        String name = rs.getString("name");
 
        return new Greeting(seq, name);
    }  
}