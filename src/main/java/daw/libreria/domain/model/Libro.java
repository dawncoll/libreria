package daw.libreria.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Libro implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = -3549508516871569479L;

	private Long id;
	    
	private String titulo;
	
	private Date primeraEdicion;
	
	private String editor;
	  
	private String descripcion;
	  
	private String isbn;
	  
	private Autor autor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getPrimeraEdicion() {
		return primeraEdicion;
	}

	public void setPrimeraEdicion(Date primeraEdicion) {
		this.primeraEdicion = primeraEdicion;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	
	
}
