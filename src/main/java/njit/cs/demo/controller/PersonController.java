package njit.cs.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import njit.cs.demo.dto.PersonDTO;
import njit.cs.demo.dto.PersonTypeDTO;
import njit.cs.demo.dto.PhoneTypeDTO;
import njit.cs.demo.resturl.RESTUrls;
import njit.cs.demo.service.PersonService;
import njit.cs.demo.service.PersonTypeService;
import njit.cs.demo.service.PhoneTypeService;

@Log4j2
@RestController
@RequestMapping(produces = "application/json")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@Autowired
	PersonTypeService personTypeService;
	
	@Autowired
	PhoneTypeService phoneTypeService;
	
	
	@RequestMapping(value = RESTUrls.URL_PERSON_ALL, method = RequestMethod.GET)
	public ResponseEntity<List<PersonDTO>> findAllPerson() 
	{
		try {
			List<PersonDTO> personDTOList = personService.findAll();
			
			if (personDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<PersonDTO>>(personDTOList, HttpStatus.OK);
			
		} catch (Exception e) {
			//log.error("Error calling contractService.get()", e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = RESTUrls.URL_PERSON_BY_UID_AND_PWD, method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> findByUserIdAndPassword(@PathVariable String uid, @PathVariable String pwd) 
	{
		try {
			PersonDTO personDTO = personService.findByUserIdAndPassword(uid.toUpperCase(), pwd);
			
			if (personDTO == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 
			return new ResponseEntity<PersonDTO>(personDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			//log.error("Error calling contractService.get()", e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = RESTUrls.URL_PERSON_BY_SSN, method = RequestMethod.GET)
	public ResponseEntity<PersonDTO> getPersonBySsn(@PathVariable Long ssn) 
	{
		try {
			PersonDTO personDTO = personService.getPersonBySsn(ssn);
			
			if (personDTO == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 
			return new ResponseEntity<PersonDTO>(personDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			//log.error("Error calling contractService.get()", e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = RESTUrls.URL_PERSON_TYPES, method = RequestMethod.GET)
	public ResponseEntity<List<PersonTypeDTO>> getPersonTypes() 
	{
		try {
			List<PersonTypeDTO> personTypeDTOList = personTypeService.listAll();
			
			if (personTypeDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<PersonTypeDTO>>(personTypeDTOList, HttpStatus.OK);
			
		} catch (Exception e) {
			//log.error("Error calling contractService.get()", e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	
	@RequestMapping(value = RESTUrls.URL_PHONE_TYPES, method = RequestMethod.GET)
	public ResponseEntity<List<PhoneTypeDTO>> getPhoneTypes() 
	{
		try {
			List<PhoneTypeDTO> phoneTypeDTOList = phoneTypeService.listAll();
			
			if (phoneTypeDTOList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<List<PhoneTypeDTO>>(phoneTypeDTOList, HttpStatus.OK);
			
		} catch (Exception e) {
			//log.error("Error calling contractService.get()", e);
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

	@RequestMapping(value = RESTUrls.URL_PERSON_CREATE, method = RequestMethod.POST)
    public PersonDTO personCreate(@RequestBody PersonDTO personDTO) {
		
		PersonDTO responseDTO = null;
	
		try {
			 responseDTO = personService.personCreate(personDTO);
		} catch (Exception e) {
		    //log.error(e.toString(), e);
			e.printStackTrace();
		}
	
		return responseDTO;
    }		

	@RequestMapping(value = RESTUrls.URL_PERSON_UPDATE, method = RequestMethod.POST)
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO) {
		
		PersonDTO responseDTO = null;
	
		try {
			 responseDTO = personService.personUpdate(personDTO);
		} catch (Exception e) {
		     //log.error(e.toString(), e);
			 e.printStackTrace();
		}
	
		return responseDTO;
    }	
	
}
