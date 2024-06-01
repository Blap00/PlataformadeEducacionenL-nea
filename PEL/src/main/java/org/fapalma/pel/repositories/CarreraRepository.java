package org.fapalma.pel.repositories;

import java.util.List;
import java.util.Optional;

import org.fapalma.pel.models.Carrera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Long>{
	
	Optional<Carrera> findById(Long id);
	
	List<Carrera> findAll();

}
