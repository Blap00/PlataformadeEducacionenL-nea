package org.fapalma.pel.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "asignaturas")
public class Asignaturas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;  //PK
	
	@Size(min=5, message="first_name must be greater than 5 characters")
	private String nomAsignatura;
	
	@ManyToMany(mappedBy="carreraId", cascade=CascadeType.ALL , fetch=FetchType.LAZY)
	private Carrera carreraId;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	public Asignaturas() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomAsignatura() {
		return nomAsignatura;
	}

	public void setNomAsignatura(String nomAsignatura) {
		this.nomAsignatura = nomAsignatura;
	}

	public Carrera getCarreraId() {
		return carreraId;
	}

	public void setCarreraId(Carrera carreraId) {
		this.carreraId = carreraId;
	}
	
	
	
}
