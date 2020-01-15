package njit.cs.demo.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;
import njit.cs.demo.domain.PhoneType;
import njit.cs.demo.dto.PhoneTypeDTO;
import njit.cs.demo.repository.PhoneTypeRepository;


@Log4j2
@Service
@Transactional
public class PhoneTypeService {

	@Autowired
	private PhoneTypeRepository phoneTypeRepository;
		
	@Transactional(readOnly = true)
	public List<PhoneTypeDTO> listAll() 
	{	
		List<PhoneType> phoneTypeList=  phoneTypeRepository.findAll();
		
		List<PhoneTypeDTO> phoneTypeListDTOs = phoneTypeList.stream().map(toPhoneTypeDTO).collect(Collectors.toList());
		
		return phoneTypeListDTOs;
	}
	
	private Function<PhoneType, PhoneTypeDTO> toPhoneTypeDTO = new Function<PhoneType, PhoneTypeDTO>() {

		public PhoneTypeDTO apply(PhoneType phoneType) {
			
			PhoneTypeDTO phoneTypeDTO = new PhoneTypeDTO();
			try {
						
				phoneTypeDTO.setId(phoneType.getId());
				phoneTypeDTO.setType(phoneType.getType());

			}catch(Exception e) {
				//log.error(e.getMessage());
			}
			return phoneTypeDTO;
		}
	};
}
