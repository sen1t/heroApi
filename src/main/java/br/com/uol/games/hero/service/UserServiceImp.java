package br.com.uol.games.hero.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.uol.games.hero.UTIL.Util;
import br.com.uol.games.hero.model.User;
import br.com.uol.games.hero.model.UserDTO;
import br.com.uol.games.hero.model.form.UserForm;
import br.com.uol.games.hero.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	private int codSuccess = 200;
	private String vingadoresURL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	private String ligaJusticaURL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

	@Autowired
	private UserRepository userRepository;

	private BufferedReader consumeAPI(String stringURL) throws Exception {
		URL url = new URL(stringURL);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() != codSuccess)
			throw new Exception("HTTP error code : " + conn.getResponseCode());

		BufferedReader response = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		return response;
	}

	@Override
	public List<String> jsonConsumer(String url) throws Exception {
		BufferedReader br = consumeAPI(url);
		return Util.jsonResponse(br);
	}

	@Override
	public List<String> xmlConsumer(String url) throws Exception {
		BufferedReader br = consumeAPI(url);
		return Util.xmlResponse(br);
	}

	public ResponseEntity saveUser(UserForm formUser) {
		User user = null;
		String hero = null;
		
		try {
		
		if(formUser.getGroupName().equalsIgnoreCase("1")) {
			formUser.setGroupName("Vingadores");
			hero = heroAvailable(jsonConsumer(vingadoresURL), formUser.getGroupName());
		}else if (formUser.getGroupName().equalsIgnoreCase("2")) {
			formUser.setGroupName("Liga Da Justiça");
			hero = heroAvailable(xmlConsumer(ligaJusticaURL), formUser.getGroupName());
		}else
			return ResponseEntity.badRequest().body("GroupName Not Found!");
			
		if(hero == null) 
			return ResponseEntity.badRequest().body("maximum number of hero reached for groupName");
		
		
		user = userRepository.saveAndFlush(new User(formUser, hero));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user));
		
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Erro interno");
		}
	}

	public String heroAvailable(List<String> hero, String groupName) throws Exception {
		Collection<UserDTO> users = userRepository.findAllByGroupName(groupName);
		// SE O ARRAY DE USUARIOS ESTIVER VAZIO, O SISTEMA DARÁ O PRIMEIRO HEROI.
		if (users.size() < 1)
			return hero.get(0);
		// SE O TAMANHO DOS ARRAYS SE EQUIVALEM ENTÃO TODOS OS HERÓIS FORAM ULTILIZADOS
		else if (users.size() == hero.size()) {
			return null;
		}

		Iterator<UserDTO> it = users.iterator();
		while (it.hasNext()) {
			if (hero.get(0).equalsIgnoreCase(it.next().getCodName())) {
				hero.remove(0);
			}
		}

		if (hero.size() > 0)
			return hero.get(0);
		else
			return null;
	}

}
