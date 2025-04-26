package daw.libreria.domain.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import daw.libreria.domain.model.Autor;
import daw.libreria.persistence.entity.AutorEntity;
import daw.libreria.persistence.pagination.BasePaginationMapper;

@Component
public class AutorMapper implements BasePaginationMapper {

	public static Autor fromAutorEntityToAutor(AutorEntity entity) {
		
		Autor autor = new Autor();
		autor.setId(entity.getId());
		autor.setDescripcion(entity.getDescripcion());
		autor.setNombre(entity.getNombre());
		autor.setPrimerApellido(entity.getPrimerApellido());
		autor.setSegundoApellido(entity.getSegundoApellido());
		return autor;
	}
	
	public Page<Autor> fromAutorEntityPgToAutorPg(Page<AutorEntity> entityPg){
		return toPage(entityPg, AutorMapper::fromAutorEntityToAutor);
	}
}
