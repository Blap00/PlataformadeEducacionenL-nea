package org.fapalma.pel.repositories;

import java.util.List;
import java.util.Optional;

import org.fapalma.pel.models.Asignaturas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends CrudRepository<Asignaturas, String>{
	
	Optional<Asignaturas> findById(String id);
	
	List<Asignaturas> findAll();

}
