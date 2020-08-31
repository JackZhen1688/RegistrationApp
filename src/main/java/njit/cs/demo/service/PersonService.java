package njit.cs.demo.service;


import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;
import njit.cs.demo.domain.Address;
import njit.cs.demo.domain.EmgContact;
import njit.cs.demo.domain.Person;
import njit.cs.demo.domain.PersonType;
import njit.cs.demo.domain.PhoneType;
import njit.cs.demo.domain.Phones;
import njit.cs.demo.dto.AddressDTO;
import njit.cs.demo.dto.EmgContactDTO;
import njit.cs.demo.dto.PersonDTO;
import njit.cs.demo.dto.PersonTypeDTO;
import njit.cs.demo.dto.PhoneTypeDTO;
import njit.cs.demo.dto.PhonesDTO;
import njit.cs.demo.repository.AddressRepository;
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
	private AddressRepository addressRepository;
	
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
		
		PersonDTO personDTO = new PersonDTO();
		//#2.Copy the data from the List of the Domain Objects into List of the DTO Objects
		if (person != null)
		    personDTO = toPersonDTO.apply(person);
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

	/* --------------------------------------------------
	 * Using ModelMapper to replace with the long setting 
	 * --------------------------------------------------*/
	/*
	private ModelMapper modelMapper = new ModelMapper();
	private Function<Person, PersonDTO> toPersonDTO = new Function<Person, PersonDTO>() {

		@Override
		public PersonDTO apply(Person person)  {
			return modelMapper.map(person, PersonDTO.class);
		}

	};
	*/
	
	private Function<Person, PersonDTO> toPersonDTO = new Function<Person, PersonDTO>() {

		public PersonDTO apply(Person person) {
			
			PersonDTO personDTO = new PersonDTO();
			try {
						
				personDTO.setId(person.getId());	
				personDTO.setPertId(person.getPertId());  
				personDTO.setUserId(person.getUserId());
				personDTO.setPassword(person.getPassword());
				personDTO.setSsn(person.getSsn());
				personDTO.setFirstName(person.getFirstName());
				personDTO.setLastName(person.getLastName());
				personDTO.setBirthDay(person.getBirthDay());
				personDTO.setAddress(toAddressDTO.apply(person.getAddress()));
				personDTO.setEmgContact(toEmgContactDTO.apply(person.getEmgContact()));
				personDTO.setPhones(person.getPhoneList().stream()
						 .map(toPhonesDTO).collect(Collectors.toList()));
				

			}catch(Exception e) {
				log.error("function error toPersonDTO()", e);
			}
			return personDTO;
		}
	};

	private Function<Address, AddressDTO> toAddressDTO = new Function<Address, AddressDTO>() {
		@Override
		public AddressDTO apply(Address address) {
			   AddressDTO addressDTO = new AddressDTO();
				if (address != null) {
					addressDTO.setId(address.getId());
					addressDTO.setStreet(address.getStreet());
					addressDTO.setCity(address.getCity());
					addressDTO.setState(address.getState());
					addressDTO.setZip(String.valueOf(address.getZip()));
				}
				return addressDTO;
			}
	};

	private Function<EmgContact, EmgContactDTO> toEmgContactDTO = new Function<EmgContact, EmgContactDTO>() {
		@Override
		public EmgContactDTO apply(EmgContact emgContact) {
				EmgContactDTO emgContactDTO = new EmgContactDTO();
				if (emgContact != null) {
					emgContactDTO.setId(emgContact.getId());
					emgContactDTO.setContactName(emgContact.getContactName());
					emgContactDTO.setContactRelation(emgContact.getContactRelation());
					emgContactDTO.setContactPhone(emgContact.getContactPhone());
					emgContactDTO.setContactEmail(emgContact.getContactEmail());
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
				phonesDTO.setPhoneType(phones.getPhoneType());
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
			person.setPertId(personDTO.getPertId());   
		    person.setUserId(personDTO.getUserId().toUpperCase());
		    person.setPassword(personDTO.getPassword());
		    person.setSsn(personDTO.getSsn());
		    person.setFirstName(personDTO.getFirstName());
		    person.setLastName(personDTO.getLastName());
		    person.setBirthDay(personDTO.getBirthDay());
			person.setAddress(toNewAddressDomain.apply(personDTO.getAddress()));
    
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
	

	Function<PersonTypeDTO, PersonType> toNewPersonTypeDomain = new Function<PersonTypeDTO, PersonType>() {
		
	    @Override
	    public PersonType apply(PersonTypeDTO personTypeDTO) {
	    	PersonType personType = new PersonType();
	    	personType.setType(personTypeDTO.getType());

			return personType;
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
	
		    person.setPertId(personDTO.getPertId());   
		    person.setUserId(personDTO.getUserId().toUpperCase());
		    person.setPassword(personDTO.getPassword());
		    person.setSsn(personDTO.getSsn());
		    person.setFirstName(personDTO.getFirstName());
		    person.setLastName(personDTO.getLastName());
		    person.setBirthDay(personDTO.getBirthDay());
		    if (personDTO.getAddress().getId() != null) {
		    	Optional<Address> address = addressRepository.findById(personDTO.getAddress().getId());
		    	if (address != null)
			        toAddressDomain.accept(personDTO.getAddress(), address.get());
		    }

		    if (personDTO.getEmgContact().getId() != null) {
		    	Optional<EmgContact> emgContact = emgRepository.findById(personDTO.getEmgContact().getId());
				if (emgContact != null)
				    toEmgContactDomain.accept(personDTO.getEmgContact(), emgContact.get());
		    } else {
		    	if (personDTO.getEmgContact().getContactName() != null ) {
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
    

	Function<AddressDTO, Address> toNewAddressDomain = new Function<AddressDTO, Address>() {
		
	    @Override
	    public Address apply(AddressDTO addressDTO) {
	    	Address address = new Address();
	    	address.setStreet(addressDTO.getStreet());
	    	address.setCity(addressDTO.getCity());
	    	address.setState(addressDTO.getState());
	    	address.setZip(Long.parseLong(addressDTO.getZip()));
	    	
			return address;
	    }
	}; 
	
	Function<EmgContactDTO, EmgContact> toNewEmgContactDomain = new Function<EmgContactDTO, EmgContact>() {
		
	    @Override
	    public EmgContact apply(EmgContactDTO emgContactDTO) {
	    	EmgContact emgContact = new EmgContact();
	    	emgContact.setContactName(emgContactDTO.getContactName());
	    	emgContact.setContactRelation(emgContactDTO.getContactRelation());
	    	emgContact.setContactPhone(emgContactDTO.getContactPhone());
	    	emgContact.setContactEmail(emgContactDTO.getContactEmail());
	    	
			return emgContact;
	    }
	};  

	BiConsumer<AddressDTO, Address> toAddressDomain = new BiConsumer<AddressDTO, Address>() {

	    @Override
	    public void accept(AddressDTO addressDTO, Address address) {
	    	address.setStreet(addressDTO.getStreet());
	    	address.setCity(addressDTO.getCity());
	    	address.setState(addressDTO.getState());
	    	if (addressDTO.getZip() != null)
	    	    address.setZip(Long.parseLong(addressDTO.getZip()));
	    }
	};
	
	BiConsumer<EmgContactDTO, EmgContact> toEmgContactDomain = new BiConsumer<EmgContactDTO, EmgContact>() {

	    @Override
	    public void accept(EmgContactDTO emgContactDTO, EmgContact emgContact) {
	    	emgContact.setContactName(emgContactDTO.getContactName());
	    	emgContact.setContactRelation(emgContactDTO.getContactRelation());
	    	emgContact.setContactPhone(emgContactDTO.getContactPhone());
	    	emgContact.setContactEmail(emgContactDTO.getContactEmail());
	    }
	};
	
	Function<PhonesDTO, Phones> toNewPhonesDomain = new Function<PhonesDTO, Phones>() {
		
	    @Override
	    public Phones apply(PhonesDTO phonesDTO) {
			Phones phones = new Phones();
			phones.setPhoneType(phonesDTO.getPhoneType());
			phones.setPhone(phonesDTO.getPhone());

			return phones;
	    }
	};    
	
	Function<PhoneTypeDTO, PhoneType> toNewPhoneTypeDomain = new Function<PhoneTypeDTO, PhoneType>() {
		
	    @Override
	    public PhoneType apply(PhoneTypeDTO phoneTypeDTO) {
	    	PhoneType phoneType = new PhoneType();
	    	phoneType.setType(phoneTypeDTO.getType());

			return phoneType;
	    }
	}; 	
	
	//6.Delete a Existing Person
	public void personDelete(Long id ) {
	    
		personRepository.deleteById(id);
	}
	
}
