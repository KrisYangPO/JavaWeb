package cart.model.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private Integer id;
	private String username;
	private String hashPassword;
	private String hashsalt;
	private String email;
	private Boolean completed;

}
