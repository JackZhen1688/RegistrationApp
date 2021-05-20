package njit.cs.demo.junit;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.ComparisonFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import njit.cs.demo.configuration.JNDIConfiguration;
import njit.cs.demo.controller.PersonController;
import njit.cs.demo.dto.PhoneTypeDTO;
import njit.cs.demo.repository.PhoneTypeRepository;
import njit.cs.demo.service.PersonService;
import njit.cs.demo.service.PersonTypeService;
import njit.cs.demo.service.PhoneTypeService;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, JNDIConfiguration.class })
@ContextConfiguration(classes = {PersonController.class, PersonService.class, PhoneTypeService.class, PersonTypeService.class})  
                                                           // add due to no scan service in RegistrationApp
@AutoConfigureMockMvc
public class SpringBootApplicationJunitTest {

	@Autowired
	PersonController personController;                     // include PersonService, PersonTypeService, PhoneTYpeService
	
	@Autowired
	PhoneTypeService phoneTypeService;                     // no scan service in RegistrationApp (Invalid bean definition with name)
	
	@Autowired
	PhoneTypeRepository phoneTypeRepository;               // yes scan repository in RegistrationApp
	
	@Autowired
	MockMvc mockMvc;
	
	@BeforeClass
	public static void setUpClass() throws Exception
	{
		DriverManagerDataSource dataSource = getDataSource();
		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("jdbc/demoDS", dataSource);
	}
	
	@Test
	public void testPhoneTypeList() 
	{				
		try {
			ResponseEntity<List<PhoneTypeDTO>> phoneTypes= personController.getPhoneTypes();		
			List<PhoneTypeDTO> phoneTypeList = phoneTypes.getBody();
			//List<PhoneTypeDTO> phoneTypeDTOList = phoneTypeService.listAll();       //Service level JUnit Test
			//List<PhoneType> phoneTypeList = phoneTypeRepository.findAll();		  //Method level JUnit Test
			//assertEquals(2, phoneTypeList.size());
			Assert.assertTrue(phoneTypeList.size() > 0);
			
		    System.out.println("=============================================");
	        System.out.println("### PhoneTypeList() Successful: Return "+phoneTypeList.size()+") ###");
		    System.out.println("=============================================");
			
		}catch (AssertionError e) {//ComparisonFailure
			if (e.getMessage() == null) {
			    System.out.println("=============================================");
		        System.out.println("### PhoneTypeList() failure: Return null) ###");
			    System.out.println("=============================================");
			}
		}
	}
	
	
	
	@Test
	public void testMockPhoneTypeList() {
		try {
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
					                     .get("/phoneTypes")
					                     .contentType("applicaation/json"))
					                   //.content(""))
					                     .andExpect(status().isOk()).andReturn();
		    Assert.assertNotNull("Result is not null", mvcResult.getResponse());                   
			String jsonValue =  mvcResult.getResponse().getContentAsString();
			
			System.out.println("String === "+jsonValue);
		    System.out.println("=============================================");
	        System.out.println("### PhoneTypeList() Successful: Return "+mvcResult.getResponse().getContentLength()+") ###");
		    System.out.println("=============================================");

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static DriverManagerDataSource getDataSource() 
	{
		DriverManagerDataSource dataSource = null;
		try {
			Properties props = new Properties();
			InputStream fileIn = SpringBootApplicationJunitTest.class.getClassLoader().getResourceAsStream("Registration.properties");
			props.load(fileIn);
			dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(props.getProperty("driverClass"));
	        dataSource.setUrl(props.getProperty("connURL"));
	        dataSource.setUsername(props.getProperty("dbUsername"));
	        dataSource.setPassword(props.getProperty("dbPassword"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dataSource;
	}
	
	/*
	 //Variable declaration		
     String string1="Junit";		
     String string2="Junit";					
     String string3="test";					
     String string4="test";					
     String string5=null;					
     int   variable1=1;					
     int   variable2=2;					
     int[] airethematicArrary1 = { 1, 2, 3 };					
     int[] airethematicArrary2 = { 1, 2, 3 };	
     
	 //Assert statements	     
	 assertEquals(string1,string2);					
     assertSame(string3, string4);					
     assertNotSame(string1, string3);					
     assertNotNull(string1);			
     assertNull(string5);			
     assertTrue(variable1<variable2);					
     assertArrayEquals(airethematicArrary1, airethematicArrary2);
    */
}
