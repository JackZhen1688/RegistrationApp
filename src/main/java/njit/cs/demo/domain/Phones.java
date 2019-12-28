package njit.cs.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"PHONES\"", schema = "Public")
public class Phones implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"SEQ_PHONES\"", allocationSize=1)
	@Column(name="\"PH_ID\"", unique=true, nullable=false)         
	private Long id;                                   	    
	
    @Column(name = "\"PTY_ID\"")
    private Long ptyId;
    
    @Column(name="\"PHONE\"")
	private String phone;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"PER_ID\"", nullable = false)
    private Person person;

    
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}



}
