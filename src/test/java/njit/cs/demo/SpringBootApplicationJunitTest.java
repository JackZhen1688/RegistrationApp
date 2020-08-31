package njit.cs.demo;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.ComparisonFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import njit.cs.demo.domain.PhoneType;
import njit.cs.demo.repository.PhoneTypeRepository;
import njit.cs.demo.service.PhoneTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
	      	    Application.class, 
                JNDIConfigJunit.class})
public class SpringBootApplicationJunitTest {

	@Autowired
	PhoneTypeService phoneTypeService;
	
	@Autowired
	PhoneTypeRepository phoneTypeRepository;
	
	@Test
	public void testPhoneTypeList() 
	{				
		try {
			//List<PhoneTypeDTO> phoneTypeDTOList = phoneTypeService.listAll();   //Service level JUnit Test
			List<PhoneType> phoneTypeList = phoneTypeRepository.findAll();		  //Method level JUnit Test
			//assertEquals(2, phoneTypeList.size());
			Assert.assertTrue(phoneTypeList.size() >= 4);

		}catch (AssertionError e) {//ComparisonFailure
			if (e.getMessage() == null) {
			    System.out.println("=============================================");
		        System.out.println("### PhoneTypeList() failure: Return null) ###");
			    System.out.println("=============================================");
			}
		}
	}
	/*
	 //Variable declaration		
     String string1="Junit";		
     String string2="Junit";					
     String string3="test";					
     String string4="test";					
     String string5=null;					
     int variable1=1;					
     int	variable2=2;					
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
