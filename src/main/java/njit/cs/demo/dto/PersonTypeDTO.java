package njit.cs.demo.dto;


import lombok.Data;

@Data
public class PersonTypeDTO {

	private Long id;                                   	    
	private String personType;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}

}
