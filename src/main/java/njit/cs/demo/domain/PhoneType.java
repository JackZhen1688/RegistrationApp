package njit.cs.demo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import njit.cs.demo.domain.Phones;

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
	
	@OneToOne(mappedBy = "phoneType")
    private Phones phone;
	
}
