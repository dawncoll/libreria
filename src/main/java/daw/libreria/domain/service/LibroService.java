package daw.libreria.domain.service;

import java.util.List;

import daw.libreria.domain.model.Libro;

public interface LibroService {

	List<Libro> findAll();

	Libro buscaPorId(Long id);

	void save(Libro theBook);

	void deleteById(int theId);

	List<Libro> buscaPorNombre(String nombre);
	
}
