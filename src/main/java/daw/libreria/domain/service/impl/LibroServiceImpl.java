package daw.libreria.domain.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import daw.libreria.domain.model.Libro;
import daw.libreria.domain.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService{

	@Override
	public List<Libro> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libro buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Libro theBook) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Libro> buscaPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}
