package br.com.dambor.qrcash.userservice.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dambor.qrcash.common.domain.User;
import br.com.dambor.qrcash.userservice.context.UserContext;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserContext userContext;
	
	@GetMapping
	public ResponseEntity<Set<User>> getUsers() {
		return ResponseEntity.ok(userContext.findAll());
	}

	@GetMapping("/{email}")
	public ResponseEntity<User> getUser(@PathVariable("email") String email) {
	
		Optional<User> user = userContext.findByEmail(email);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User userCreated = userContext.createUser(user);
		
		return ResponseEntity.ok(userCreated);
	}

}
