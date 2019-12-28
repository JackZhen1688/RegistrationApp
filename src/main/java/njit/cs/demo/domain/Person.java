package njit.cs.demo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import njit.cs.demo.domain.EmgContact;

import lombok.Data;

@Data
@Entity
@Table(name = "\"PERSON\"", schema = "Public")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_Sequence")
	@SequenceGenerator(name="id_Sequence", schema="Public", sequenceName="\"SEQ_PERSON\"", allocationSize=1)
	@Column(name="\"PER_ID\"", unique=true, nullable=false)         
	private Long id;                                   	    

	@Column(name="\"UID\"")
	private String userId;

	@Column(name="\"PWD\"")
	private String password;

	@Column(name="\"FIRST_NAME\"")
	private String firstName;

	@Column(name="\"LAST_NAME\"")
	private String lastName;

	@Column(name="\"SSN\"")
	private Long ssn;

	@Column(name="\"BIRTH_DAY\"")
	private Date birthDay;
	
	
	//Person(Foreign key:PERT_ID) --> Phones(Primary key:PERT_ID)
	//@OneToOne  (cascade = {CascadeType.ALL}) 
    @Column(name = "\"PERT_ID\"")
    private Long pertId;
    
    //Person(Primary key:PER_ID) --> Phones(Foreign key:PER_ID)
	@OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)     
	private EmgContact emgContact;                                          

	//Person(Primary key:PER_ID) --> Phones(Foreign key:PER_ID)
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Phones> phoneList = Lists.newArrayList();                  
   
    
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

	public EmgContact getEmgContact() {
		return emgContact;
	}

	public void setEmgContact(EmgContact emgContact) {
		this.emgContact = emgContact;
	}

	public List<Phones> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phones> phoneList) {
		this.phoneList = phoneList;
	}


}
