package br.com.dambor.qrcash.userservice.context;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

import br.com.dambor.qrcash.common.domain.User;
import br.com.dambor.qrcash.userservice.exception.DuplicatedRegisterException;

@Component
public class UserContext {

	private Set<User> context = new HashSet<>();
	private long id = 0;
	
	public Optional<User> findByEmail(String email) {
		// Finding user in the context
		return context.stream().filter(u -> u.getEmail().equals(email)).findFirst();
	}

	public User createUser(User user) {
		
		Optional<User> userFound = findByEmail(user.getEmail());
		
		if(userFound.isPresent()) {
			throw new DuplicatedRegisterException(String.format("Cannot add user with same email [%s]", user.getEmail()));
		}
		// Adding user to the context
		user.setId(++id);
		context.add(user);
		
		return user;
	}

	public Set<User> findAll() {
		return context;
	}
	
}
