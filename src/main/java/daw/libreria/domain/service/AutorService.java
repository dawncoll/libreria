package daw.libreria.domain.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import daw.libreria.domain.model.Autor;

public interface AutorService {

	Page<Autor> findAll(Pageable pageable);

    void save(Autor autor);

    void deleteById(Long id);

    Optional<Autor> getAutor(Long id);
	
}
