package njit.cs.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import njit.cs.demo.domain.PhoneType;
import njit.cs.demo.dto.PhoneTypeDTO;
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
	public void getUserTest() {
		
		List<PhoneTypeDTO> phoneTypeDTOList = phoneTypeService.listAll();
		assertEquals(3,phoneTypeDTOList.size());
		
		List<PhoneType> phoneTypeList = phoneTypeRepository.findAll();		
		assertEquals(3,phoneTypeList.size());
		System.out.println("##### phoneTypeList.size(): "+phoneTypeList.size());
	}
	
}
