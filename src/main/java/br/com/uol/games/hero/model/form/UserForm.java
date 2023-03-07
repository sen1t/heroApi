package br.com.uol.games.hero.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserForm {
	private String name;
	private String email;
	private String phone;
	private String groupName;
}
