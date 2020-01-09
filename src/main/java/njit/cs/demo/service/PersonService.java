package njit.cs.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;
import njit.cs.demo.domain.EmgContact;
import njit.cs.demo.domain.Person;
import njit.cs.demo.domain.PersonType;
import njit.cs.demo.domain.Phones;
import njit.cs.demo.dto.EmgContactDTO;
import njit.cs.demo.dto.PersonDTO;
import njit.cs.demo.dto.PersonTypeDTO;
import njit.cs.demo.dto.PhonesDTO;
import njit.cs.demo.repository.EmgRepository;
import njit.cs.demo.repository.PersonRepository;
import njit.cs.demo.repository.PhonesRepository;


@Log4j2
@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PhonesRepository phonesRepository;
	
	@Autowired
	private EmgRepository emgRepository;

	//1.Retrieve Person All List
	public List<PersonDTO> findAll() 
	{	
		//log.info("Find all records method starting...");
		//#1.Retrieve the date from DB and fill up into List of the Domain Objects.
		List<Person> personList=  personRepository.findAll();
		
		//#2.Copy the data from the List of the Domain Objects into List of the DTO Objects
		List<PersonDTO> personListDTOs = personList.stream().map(toPersonDTO).collect(Collectors.toList());
		//List<ContractDTO> listContractDTOs = Lists.transform(domainList, toContractDTO);
		
		return personListDTOs;
	}

	//2.Retrieve One Person By UID and PWD
	public PersonDTO findByUserIdAndPassword(String uid, String pwd) 
	{	
		Person person =  personRepository.findByUserIdAndPassword(uid, pwd);
		
		//#2.Copy the data from the List of the Domain Objects into List of the DTO Objects
		PersonDTO personDTO = toPersonDTO.apply(person);
		//List<ContractDTO> listContractDTOs = Lists.transform(domainList, toContractDTO);
		
		return personDTO;
	}

    //3.Retrieve One Person By SSN	
	public PersonDTO getPersonBySsn(Long ssn) 
	{	
		Person person=  personRepository.findBySsn(ssn);
		
		PersonDTO personDTOs = toPersonDTO.apply(person);
		
		return personDTOs;
	}

	private Function<Person, PersonDTO> toPersonDTO = new Function<Person, PersonDTO>() {

		public PersonDTO apply(Person person) {
			
			PersonDTO personDTO = new PersonDTO();
			try {
						
				personDTO.setId(person.getId());	
				//personDTO.setPertId(person.getPertId());
				personDTO.setPersonType(toPersonTypeDTO.apply(person.getPersonType()));
				personDTO.setUserId(person.getUserId());
				personDTO.setPassword(person.getPassword());
				personDTO.setSsn(person.getSsn());
				personDTO.setFirstName(person.getFirstName());
				personDTO.setLastName(person.getLastName());
				personDTO.setBirthDay(person.getBirthDay());
				personDTO.setEmgContact(toEmgContactDTO.apply(person.getEmgContact()));
				personDTO.setPhones(person.getPhoneList().stream()
						 .map(toPhonesDTO).collect(Collectors.toList()));
				

			}catch(Exception e) {
				//log.error(e.getMessage());
			}
			return personDTO;
		}
	};
	
	private Function<PersonType, PersonTypeDTO> toPersonTypeDTO = new Function<PersonType, PersonTypeDTO>() {
		@Override
		public PersonTypeDTO apply(PersonType personType) {
			    PersonTypeDTO personTypeDTO = new PersonTypeDTO();
				if (personType != null) {
					personTypeDTO.setId(personType.getId());
					personTypeDTO.setPersonType(personType.getPersonType());
				}
				return personTypeDTO;
			}
	};

	private Function<EmgContact, EmgContactDTO> toEmgContactDTO = new Function<EmgContact, EmgContactDTO>() {
		@Override
		public EmgContactDTO apply(EmgContact emgContact) {
				EmgContactDTO emgContactDTO = new EmgContactDTO();
				if (emgContact != null) {
					emgContactDTO.setId(emgContact.getId());
					emgContactDTO.setCtName(emgContact.getCtName());
					emgContactDTO.setCtRelation(emgContact.getCtRelation());
					emgContactDTO.setCtPhone(emgContact.getCtPhone());
					emgContactDTO.setCtEmail(emgContact.getCtEmail());
				}
				return emgContactDTO;
			}
	};
	
    private Function<Phones, PhonesDTO> toPhonesDTO = new Function<Phones, PhonesDTO>() {
		@Override
		public PhonesDTO apply(Phones phones) {

			PhonesDTO phonesDTO = new PhonesDTO();

			if (phones != null) {
				phonesDTO.setId(phones.getId());
				phonesDTO.setPtyId(phones.getPtyId());
				phonesDTO.setPhone(phones.getPhone());
			}

			return phonesDTO;
		}
    };
    
 
    //4.Create a New Person 
	public PersonDTO personCreate(PersonDTO personDTO) {
		
		Person person = toNewPersonDomain.apply(personDTO);
	    personRepository.save(person);

		return personDTO;
	}    
    
	Function<PersonDTO, Person> toNewPersonDomain = new Function<PersonDTO, Person>() 
 	{
		@Override
		public Person apply(PersonDTO personDTO) 
		{
			Person person = new Person();
			//person.setPertId(personDTO.getPertId());
		    person.setUserId(personDTO.getUserId().toUpperCase());
		    person.setPassword(personDTO.getPassword());
		    person.setSsn(personDTO.getSsn());
		    person.setFirstName(personDTO.getFirstName());
		    person.setLastName(personDTO.getLastName());
		    person.setBirthDay(personDTO.getBirthDay());
		    
			EmgContact emgContact = toNewEmgContactDomain.apply(personDTO.getEmgContact());
			emgContact.setPerson(person);                                     
			person.setEmgContact(emgContact);
    
		    if (!personDTO.getPhones().isEmpty()) {
				for (PhonesDTO phonesDTO : personDTO.getPhones()) {
					 Phones phones = toNewPhonesDomain.apply(phonesDTO);  
				 	 phones.setPerson(person);
				  	 person.getPhoneList().add(phones);             
				}
		    }
			
			return person;
		}
	};	
    
	
	//5.Update a Existing Person
	public PersonDTO personUpdate(PersonDTO personDTO) {
		
		//Person person = personRepository.findOne(personDTO.getId());
		Optional<Person> person =  personRepository.findById(personDTO.getId());
		
		if (person == null)
		    throw new RuntimeException(String.format("Cannot find person record with id '%d'", personDTO.getId()));

		personDomain.accept(personDTO, person.get());

		return personDTO;
	 
	}

    BiConsumer<PersonDTO, Person> personDomain = new BiConsumer<PersonDTO, Person>() {

		@Override
		public void accept(PersonDTO personDTO, Person person) {
	
		    //person.setPertId(personDTO.getPertId());
		    person.setUserId(personDTO.getUserId().toUpperCase());
		    person.setPassword(personDTO.getPassword());
		    person.setSsn(personDTO.getSsn());
		    person.setFirstName(personDTO.getFirstName());
		    person.setLastName(personDTO.getLastName());
		    person.setBirthDay(personDTO.getBirthDay());

		    if (personDTO.getEmgContact().getId() != null) {
				//EmgContact emgContact = emgRepository.findOne(personDTO.getEmgContact().getId());
		    	Optional<EmgContact> emgContact =  emgRepository.findById(personDTO.getId());
				if (emgContact != null)
				    toEmgContactDomain.accept(personDTO.getEmgContact(), emgContact.get());
		    } else {
		    	if (personDTO.getEmgContact().getCtName() != null ) {
					EmgContact emgContact = toNewEmgContactDomain.apply(personDTO.getEmgContact());
					emgContact.setPerson(person);                                     
					person.setEmgContact(emgContact);
		    	}
		    }
				
			phonesRepository.deleteInBatch(person.getPhoneList());
			person.getPhoneList().clear();

		    if (!personDTO.getPhones().isEmpty()) {
				for (PhonesDTO phonesDTO : personDTO.getPhones()) {
					 Phones phones = toNewPhonesDomain.apply(phonesDTO);  
				 	 phones.setPerson(person);
				  	 person.getPhoneList().add(phones);             
				}
		    }

		}
		
    };
    
    
	Function<EmgContactDTO, EmgContact> toNewEmgContactDomain = new Function<EmgContactDTO, EmgContact>() {
		
	    @Override
	    public EmgContact apply(EmgContactDTO emgContactDTO) {
	    	EmgContact emgContact = new EmgContact();
	    	emgContact.setCtName(emgContactDTO.getCtName());
	    	emgContact.setCtRelation(emgContactDTO.getCtRelation());
	    	emgContact.setCtPhone(emgContactDTO.getCtPhone());
	    	emgContact.setCtEmail(emgContactDTO.getCtEmail());

			return emgContact;
	    }
	};  
	
	BiConsumer<EmgContactDTO, EmgContact> toEmgContactDomain = new BiConsumer<EmgContactDTO, EmgContact>() {

	    @Override
	    public void accept(EmgContactDTO emgContactDTO, EmgContact emgContact) {
	    	emgContact.setCtName(emgContactDTO.getCtName());
	    	emgContact.setCtRelation(emgContactDTO.getCtRelation());
	    	emgContact.setCtPhone(emgContactDTO.getCtPhone());
	    	emgContact.setCtEmail(emgContactDTO.getCtEmail());
	    }
	};
	
	Function<PhonesDTO, Phones> toNewPhonesDomain = new Function<PhonesDTO, Phones>() {
		
	    @Override
	    public Phones apply(PhonesDTO phonesDTO) {
			Phones phones = new Phones();
			phones.setPtyId(phonesDTO.getPtyId());
			phones.setPhone(phonesDTO.getPhone());

			return phones;
	    }
	};    

	
}