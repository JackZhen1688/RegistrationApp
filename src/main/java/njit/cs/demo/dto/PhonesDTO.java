package njit.cs.demo.dto;


import lombok.Data;

@Data
public class PhonesDTO {

	private Long id;   
    private Long ptyId;
	private String phone;

 	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPtyId() {
		return ptyId;
	}
	public void setPtyId(Long ptyId) {
		this.ptyId = ptyId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

    
}
