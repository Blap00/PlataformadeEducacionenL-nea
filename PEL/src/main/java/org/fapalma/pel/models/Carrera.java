package org.fapalma.pel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "carrera")
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  //PK
	
	@Size(min=8, message="Password must be greater than 8 characters")
	private String carrera_name;
	
	public Carrera() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarrera_name() {
		return carrera_name;
	}

	public void setCarrera_name(String carrera_name) {
		this.carrera_name = carrera_name;
	}
	
	
}
