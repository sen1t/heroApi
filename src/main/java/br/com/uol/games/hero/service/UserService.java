package br.com.uol.games.hero.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.uol.games.hero.model.form.UserForm;

public interface UserService {

	List<String> jsonConsumer(String url) throws Exception;
	List<String> xmlConsumer(String url) throws Exception;
	ResponseEntity saveUser(UserForm formUser);
	
}
