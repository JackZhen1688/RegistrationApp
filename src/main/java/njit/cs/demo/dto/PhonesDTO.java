package njit.cs.demo.dto;


import lombok.Data;

@Data
public class PhonesDTO {

	private Long id;   
    //private Long ptyId;  <-- Ignore mapping column
	private String phone;
	private String phoneType;
	
	/*-----------------------------------
	 * Ignore it for now. Deal with later
	 *-----------------------------------*/
	//private PhoneTypeDTO phoneType;
	
}
