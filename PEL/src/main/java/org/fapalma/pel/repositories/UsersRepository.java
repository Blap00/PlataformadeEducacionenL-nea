package org.fapalma.pel.repositories;

import java.util.List;
import java.util.Optional;

import org.fapalma.pel.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long>{
	
	Optional<Users> findById(Long id);
	
	List<Users> findAll();

	Users findByEmail(String email);

	Users findByUsername(String username);

}
