package org.fapalma.pel.services;

import java.util.List;
import java.util.Optional;

import org.fapalma.pel.models.Asignaturas;
import org.fapalma.pel.models.Carrera;
import org.fapalma.pel.repositories.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsiganturaServices {
	@Autowired
	private final AsignaturaRepository asignRepository;

	public AsiganturaServices(AsignaturaRepository asignRepository) {
		this.asignRepository = asignRepository;
	}
	
	public List<Asignaturas> findAll(){
		return asignRepository.findAll();
	}
	
	public Asignaturas findByID(String id) {
		Optional<Asignaturas> opAsign = asignRepository.findById(id);
		if(opAsign.isPresent()) {
			return opAsign.get();
		}else {
			throw new IllegalArgumentException("No se encontró ninguna Asignatura con la ID proporcionada!.");
		}
	}
	
	public Asignaturas createAsignatura(Asignaturas as) {
		return asignRepository.save(as);
	}
	
	public Asignaturas updateAsignatura(String id, String nomAsignatura, Carrera carreraId) {
    	Optional<Asignaturas> opAsign = asignRepository.findById(id);
    	if(opAsign.isPresent()) {
    		Asignaturas asignatura = opAsign.get();
    		asignatura.setNomAsignatura(nomAsignatura);
    		asignatura.setCarreraId(carreraId);
    		return asignRepository.save(asignatura);
    	}else {
    		throw new IllegalArgumentException("No se encontró ninguna asignatura con el ID proporcionado!.");
    	}
    }
	
	public void deleteAsignatura(String id) {
		Optional<Asignaturas> opAsign = asignRepository.findById(id);
		if(opAsign.isPresent()) {
			asignRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("No se encontró ninguna Asignatura con el ID proporcionado!.");
		}
	}
	
}
