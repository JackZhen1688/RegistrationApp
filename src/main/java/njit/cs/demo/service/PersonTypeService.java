package njit.cs.demo.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;
import njit.cs.demo.domain.PersonType;
import njit.cs.demo.dto.PersonTypeDTO;
import njit.cs.demo.repository.PersonTypeRepository;


@Log4j2
@Service
@Transactional
public class PersonTypeService {
	
	@Autowired
	private PersonTypeRepository personTypeRepository;
	
	@Transactional(readOnly = true)
	public List<PersonTypeDTO> listAll() 
	{	
		List<PersonType> personTypeList=  personTypeRepository.findAll();
		
		List<PersonTypeDTO> personListDTOs = personTypeList.stream().map(toPersonTypeDTO).collect(Collectors.toList());
		
		return personListDTOs;
	}
	
	private Function<PersonType, PersonTypeDTO> toPersonTypeDTO = new Function<PersonType, PersonTypeDTO>() {

		public PersonTypeDTO apply(PersonType personType) {
			
			PersonTypeDTO personTypeDTO = new PersonTypeDTO();
			try {
						
				personTypeDTO.setId(personType.getId());
				personTypeDTO.setType(personType.getType());

			}catch(Exception e) {
				//log.error(e.getMessage());
			}
			return personTypeDTO;
		}
	};
	
	public PersonTypeDTO getPersonTypeByType(String type) 
	{	
		PersonType personType=  personTypeRepository.findByType(type);
		
		PersonTypeDTO personTypeDTO = toPersonTypeDTO.apply(personType);
		
		return personTypeDTO;
	}
	
}
