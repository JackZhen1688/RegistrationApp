package njit.cs.demo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"PHONE_TYPE\"", schema = "Public")
public class PhoneType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"PTY_ID\"")         
	private Long id;                                   	    
	                                                             
	@Column(name="\"PHONE_TYPE\"")
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
