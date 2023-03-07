package br.com.uol.games.hero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.games.hero.model.User;
import br.com.uol.games.hero.model.form.UserForm;
import br.com.uol.games.hero.repository.UserRepository;
import br.com.uol.games.hero.service.UserServiceImp;

@RestController
@RequestMapping("herogame/")
public class UserController {

	@Autowired	
	UserServiceImp heroService;
	
	@Autowired
	UserRepository userRep;
	
	@PostMapping
	@RequestMapping("save")
	public ResponseEntity saveUser(@RequestBody UserForm userForm) {
			return heroService.saveUser(userForm);
	}
	
	@GetMapping
	public ResponseEntity listar(){
		List<User> list = userRep.findAll();
		return ResponseEntity.ok(list);	
	}
	
}
