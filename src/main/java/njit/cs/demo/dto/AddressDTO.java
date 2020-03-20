package njit.cs.demo.dto;


import lombok.Data;

@Data
public class AddressDTO {

	private Long id;     
	private String street;
	private String city;
	private String state;
	private String zip;
	
}
