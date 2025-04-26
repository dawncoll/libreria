package daw.libreria.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import daw.libreria.persistence.entity.AutorEntity;

public interface AutorRepository extends PagingAndSortingRepository<AutorEntity, Integer>, CrudRepository<AutorEntity, Integer>{

}
