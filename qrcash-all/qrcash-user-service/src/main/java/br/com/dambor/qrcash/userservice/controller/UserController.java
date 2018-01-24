package br.com.dambor.qrcash.userservice.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dambor.qrcash.common.domain.User;
import br.com.dambor.qrcash.userservice.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Iterable<User>> getUsers() {
		return ResponseEntity.ok(userService.findAll());
	}

	@GetMapping("/{email}")
	public ResponseEntity<User> getUser(@PathVariable("email") String email) {
	
		Optional<User> user = userService.findByEmail(email);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		User userCreated = userService.save(user);
		
		return ResponseEntity.ok(userCreated);
	}

}
