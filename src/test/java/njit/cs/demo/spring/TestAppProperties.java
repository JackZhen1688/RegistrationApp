package njit.cs.demo.spring;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


@Log4j2
public class TestAppProperties {

	protected static DataSource dataSource;
	protected static ApplicationProperties appProperties;

	public static void main(String[] args) {
		
		TestAppProperties testAppProperties = new TestAppProperties();

		//#1. Testing the Application Properties Bean
		appProperties = (ApplicationProperties) testAppProperties.getAppProperties("appProperties");
		log.debug("getJdbcPassword()="+appProperties.getJdbcPassword());
		log.debug("getJdbcUsername()="+appProperties.getJdbcUsername());
		log.debug("getJavaVersion()="+appProperties.getDriverClass());
				
		//#2. Test DriverManagerDataSource		
		dataSource = (DataSource) testAppProperties.getAppProperties("dataSource");
		try {
			
			Connection dbConn = dataSource.getConnection();
			String sql = "SELECT CURRENT_TIME";
			Statement stm = dbConn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				System.out.println("##### urrent time:"+rs.getString(1));				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
				
	//Return a Object Bean (id=?) which is defined in appproperty-config.xml
	public Object getAppProperties(String strBeanName) {
		
		ApplicationContext ctx= new FileSystemXmlApplicationContext("src/test/java/njit/cs/demo/spring/config/appproperty-config.xml");
		Object appProperties = (Object) ctx.getBean(strBeanName);
		
		return appProperties;
	}
	
	
}
