package br.com.dambor.qrcash.userservice.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.dambor.qrcash.common.domain.User;

/**
 * Repository for {@link User} entity.
 * 
 * @author saulo.borges
 *
 */
public interface IUserRepository extends PagingAndSortingRepository<User, Long>{

	/**
	 * 
	 * @param email
	 * @return 
	 */
	public Optional<User> findByEmail(String email);
}
