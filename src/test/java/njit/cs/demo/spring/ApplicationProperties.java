package njit.cs.demo.spring;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service("appProperties")  /*---------------------------------------------------------
                             1. defined id in appproperty-config.xml 
                             2. load the value into the bean from the properties file
                             ---------------------------------------------------------*/
public class ApplicationProperties implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String jdbcUsername;
	private String jdbcPassword;
	private String driverClass;
	private String connURL;
	
	public String getJdbcUsername() {
		return jdbcUsername;
	}
	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}
	public String getJdbcPassword() {
		return jdbcPassword;
	}
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getConnURL() {
		return connURL;
	}
	public void setConnURL(String connURL) {
		this.connURL = connURL;
	}	
	
}
