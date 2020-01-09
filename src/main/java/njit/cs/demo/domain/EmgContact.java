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
	
	
}
