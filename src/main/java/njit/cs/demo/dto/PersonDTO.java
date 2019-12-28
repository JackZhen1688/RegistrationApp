package njit.cs.demo.dto;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import lombok.Data;
import njit.cs.demo.util.CustomDateDeserializer;
import njit.cs.demo.util.CustomDateSerializer;


@Data
public class PersonDTO {

	private Long id;     
    private Long pertId;
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private Long ssn;
	@JsonDeserialize(using = CustomDateDeserializer.class)     // java.util.Date object with JSON format
	@JsonSerialize(using   = CustomDateSerializer.class)
	private Date birthDay;
	
	private EmgContactDTO emgContact; 
    private List<PhonesDTO> phones = Lists.newArrayList();
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getPertId() {
		return pertId;
	}
	public void setPertId(Long pertId) {
		this.pertId = pertId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Long getSsn() {
		return ssn;
	}
	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public EmgContactDTO getEmgContact() {
		return emgContact;
	}
	public void setEmgContact(EmgContactDTO emgContact) {
		this.emgContact = emgContact;
	}
	public List<PhonesDTO> getPhones() {
		return phones;
	}
	public void setPhones(List<PhonesDTO> phones) {
		this.phones = phones;
	}


}
