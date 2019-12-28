package njit.cs.demo.dto;


import lombok.Data;

@Data
public class EmgContactDTO {

	private Long id;     
	private String ctName;
	private String ctRelation;
	private String ctPhone;
	private String ctEmail;
	private String perId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCtName() {
		return ctName;
	}
	public void setCtName(String ctName) {
		this.ctName = ctName;
	}
	public String getCtRelation() {
		return ctRelation;
	}
	public void setCtRelation(String ctRelation) {
		this.ctRelation = ctRelation;
	}
	public String getCtPhone() {
		return ctPhone;
	}
	public void setCtPhone(String ctPhone) {
		this.ctPhone = ctPhone;
	}
	public String getCtEmail() {
		return ctEmail;
	}
	public void setCtEmail(String ctEmail) {
		this.ctEmail = ctEmail;
	}
	public String getPerId() {
		return perId;
	}
	public void setPerId(String perId) {
		this.perId = perId;
	}
	
	
}
