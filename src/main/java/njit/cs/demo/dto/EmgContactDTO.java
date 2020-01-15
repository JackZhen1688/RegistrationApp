package njit.cs.demo.dto;


import lombok.Data;

@Data
public class EmgContactDTO {

	private Long id;     
	private String ctName;
	private String ctRelation;
	private String ctPhone;
	private String ctEmail;
	//private String perId;  <-- Ignore mapping column
	
}
