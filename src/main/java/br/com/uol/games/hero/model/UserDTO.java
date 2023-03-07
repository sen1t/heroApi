package br.com.uol.games.hero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
	private String codName;
	private String name;
	private String email;
	private String phoneNumber;
	private String groupName;
	
	
	public UserDTO(User user) {
		this.codName = user.getCodName();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		this.groupName = user.getGroupName();
	}
	
}
