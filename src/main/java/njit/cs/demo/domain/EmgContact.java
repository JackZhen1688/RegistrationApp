package njit.cs.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "\"EMGCONTACT\"", schema = "Public")
public class EmgContact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"SEQ_EMGCONTACT\"", allocationSize=1)
	@Column(name="\"ECT_ID\"", unique=true, nullable=false)         
	private Long id;     

	@Column(name="\"CONTACT_NAME\"")
	private String ctName;
	
	@Column(name="\"CONTACT_RELATION\"")
	private String ctRelation;
	
	@Column(name="\"CONTACT_PHONE\"")
	private String ctPhone;
	
	@Column(name="\"CONTACT_EMAIL\"")
	private String ctEmail;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="\"PER_ID\"", nullable=false)
	private Person person;
	

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
	
}
