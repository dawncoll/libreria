package daw.libreria.domain.service;


import daw.libreria.domain.mapper.AutorMapper;
import daw.libreria.domain.model.Autor;
import daw.libreria.domain.service.impl.AutorServiceImpl;
import daw.libreria.persistence.entity.AutorEntity;
import daw.libreria.persistence.pagination.PageHelper;
import daw.libreria.persistence.repository.AutorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
	
class AutorServiceUnitTest {

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AutorMapper autorMapper;
    
    @InjectMocks
    private AutorServiceImpl autorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializar los mocks
    }

    @Test
    @DisplayName("Debe devolver una página vacía si no hay autores")
    void testFindAllEmpty() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 5);
       
        when(autorRepository.findAll(pageable)).thenReturn(PageHelper.emptyPage());
        when(autorMapper.fromAutorEntityPgToAutorPg(Mockito.any())).thenReturn(PageHelper.emptyPage());
        
        // Act
        Page<Autor> result = autorServiceImpl.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty(), "La página debería estar vacía");
        verify(autorRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Debe devolver una página paginada con autores")
    void testFindAllWithPagination() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 2); // Página 0, máximo 2 elementos

        AutorEntity autorEntity1 = createAutorEntity(1L, "Gabriel","García");
        
        Autor autor1 = createAutor(1L, "Gabriel","García");
        Autor autor2 = createAutor(2L, "Ursula","Kroeber Le Guin");


        when(autorRepository.findAll(pageable)).thenReturn(PageHelper.toPage(List.of(autorEntity1)));
        when(autorMapper.fromAutorEntityPgToAutorPg(Mockito.any())).thenReturn(PageHelper.toPage(List.of(autor1, autor2)));

        // Act
        Page<Autor> result = autorServiceImpl.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size(), "Debe devolver 2 autores en la primera página");
        verify(autorRepository, times(1)).findAll(pageable);
    }
    
    
    /**
     * Código repetido para que alumnos lo detectem y refactoricen
     * 
     * @param id
     * @param nombre
     * @param primerApellido
     * @return
     */
    private AutorEntity createAutorEntity(Long id, String nombre, String primerApellido) {
   	 
    	AutorEntity autor = new AutorEntity();
    	autor.setId(id);
    	autor.setNombre(nombre);
        autor.setPrimerApellido(primerApellido);
        return autor;
    }
    
    private Autor createAutor(Long id, String nombre, String primerApellido) {
      	 
    	Autor autor = new Autor();
    	autor.setId(id);
    	autor.setNombre(nombre);
        autor.setPrimerApellido(primerApellido);
        return autor;
    }
}

