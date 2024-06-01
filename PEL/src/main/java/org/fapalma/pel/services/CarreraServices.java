 package org.fapalma.pel.services;

import java.util.List;
import java.util.Optional;

import org.fapalma.pel.models.Carrera;
import org.fapalma.pel.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraServices {
	@Autowired
	private final CarreraRepository carreRepository;

	public CarreraServices(CarreraRepository carreRepository) {
		this.carreRepository = carreRepository;
	}
	
	public List<Carrera> findAll(){
		return carreRepository.findAll();
	}
	
	public Carrera findByID(Long id) {
		Optional<Carrera> opCarrer = carreRepository.findById(id);
		if(opCarrer.isPresent()) {
			return opCarrer.get();
		}else {
			throw new IllegalArgumentException("No se encontró ninguna carrera con la ID proporcionada!.");
		}
	}
	
	public Carrera createCarrera(Carrera as) {
		return carreRepository.save(as);
	}
	
	public Carrera updateCarrera(Long id, String carrera_name) {
    	Optional<Carrera> opCarrer = carreRepository.findById(id);
    	if(opCarrer.isPresent()) {
    		Carrera carrera = opCarrer.get();
    		carrera.setCarrera_name(carrera_name);

    		return carreRepository.save(carrera);
    	}else {
    		throw new IllegalArgumentException("No se encontró ninguna carrera con el ID proporcionado!.");
    	}
    }
	
	public void deleteCarrera(Long id) {
		Optional<Carrera> opCarrer = carreRepository.findById(id);
		if(opCarrer.isPresent()) {
			carreRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("No se encontró ninguna carrera con el ID proporcionado!.");
		}
	}
	
}
