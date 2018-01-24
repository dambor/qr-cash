package br.com.dambor.qrcash.userservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dambor.qrcash.common.domain.User;
import br.com.dambor.qrcash.common.util.CryptUtil;
import br.com.dambor.qrcash.userservice.repository.IUserRepository;

@Component
public class UserService {

	@Autowired
	private IUserRepository userRepo;
	
	public User save(User user) {
		if(user.getId() == null) {
			// new user
			user.setCreateDate(LocalDateTime.now());
		}
		// Encrypt user password
		user.setPassword(CryptUtil.encrypt(user.getPassword()));
		return userRepo.save(user);
	}

	public Iterable<User> findAll() {
		return userRepo.findAll();
	}

	public Optional<User> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
}
