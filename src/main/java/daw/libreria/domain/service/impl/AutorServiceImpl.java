package daw.libreria.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import daw.libreria.domain.mapper.AutorMapper;
import daw.libreria.domain.model.Autor;
import daw.libreria.domain.service.AutorService;
import daw.libreria.persistence.entity.AutorEntity;
import daw.libreria.persistence.repository.AutorRepository;

@Service
public class AutorServiceImpl implements AutorService{

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private AutorMapper autorMapper;
	
	@Override
	public Page<Autor> findAll(Pageable pageable) {
		
		Page<AutorEntity> autorEntityPg = autorRepository.findAll(pageable);
		return autorMapper.fromAutorEntityPgToAutorPg(autorEntityPg);
	}

	@Override
	public void save(Autor autor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Autor> getAutor(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
