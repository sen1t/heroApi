package br.com.uol.games.hero.model;

import br.com.uol.games.hero.model.form.UserForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
public class User {
	
	@Id
	private String codName;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	private String phoneNumber;
	private String groupName;
	
	public User() {};
	
	public User(UserForm formUser, String codName) {
		   this.codName 	= codName;
		   this.name 		= formUser.getName();
		   this.email   	= formUser.getEmail(); 
		   this.phoneNumber = formUser.getPhone();
		   this.groupName	= formUser.getGroupName();
	}

}
