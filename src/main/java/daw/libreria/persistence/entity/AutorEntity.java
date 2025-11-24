package daw.libreria.persistence.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Autor")
public class AutorEntity {

	 @Id
	 private Long id;

	 @Column(name = "nombre")
	 private String nombre;
	 
	 @Column(name = "primer_apellido")
	 private String primerApellido;
	 
	 @Column(name = "segundo_apellido")
	 private String segundoApellido;
	
	 @Column(name = "descripcion")
	 private String descripcion;
	 
	 @Column(name = "ciudad_nacimiento")
	 private String ciudadNacimiento;
		
	 @Column(name = "fecha_nacimiento")
	 private Date fechaNacimiento;
<<<<<<< HEAD
	 
	 @Column(name = "numero_seguridad_social")
	 private String numeroSeguridadSocial;
		
	 public String getCiudadNacimiento() {
			return ciudadNacimiento;
		}

		public void setCiudadNacimiento(String ciudadNacimiento) {
			this.ciudadNacimiento = ciudadNacimiento;
		}

		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}

		public void setFechaNacimiento(Date fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}
	 
	public String getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
=======

	public String getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(String ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
>>>>>>> branch 'prova_rebase' of https://github.com/dawncoll/libreria.git
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	 
	 
}
