package njit.cs.demo.dto;


import lombok.Data;

@Data
public class PhoneTypeDTO {

	private Long id;                                   	    
	private String phoneType;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	
}
