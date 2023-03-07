package br.com.uol.games.hero.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.uol.games.hero.model.User;
import br.com.uol.games.hero.model.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
		@Query("SELECT NEW br.com.uol.games.hero.model.UserDTO(u.codName, u.name, u.email, u.phoneNumber, u.groupName) FROM"
				+ " br.com.uol.games.hero.model.User u "
				+ "WHERE u.groupName =:groupName")
		Collection<UserDTO> findAllByGroupName(@Param("groupName") String groupName );
	
}
