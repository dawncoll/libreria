package daw.libreria.persistence.repository;

import static org.junit.jupiter.api.Assertions.*;

import daw.libreria.persistence.entity.AutorEntity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles(value = "test")
class AutorRepositoryUnitTest {

    @Autowired
    private AutorRepository autorRepository;

    AutorEntity autor1, autor2, autor3;
    
    @BeforeEach
    void setUp() {
        
    	
    	// Initialize test data before each test method
    	 autor1 = createAutorEntity(1L, "Gabriel","García");

         autor2 = createAutorEntity(2L, "Ursula","Kroeber Le Guin");
         
         autor3 = createAutorEntity(3L, "Irene","Sola");

         autorRepository.saveAll(List.of(autor1, autor2, autor3));
    }

    @AfterEach
    void tearDown() {
        // Release test data after each test method
    	autorRepository.delete(autor1);
    	autorRepository.delete(autor2);
    	autorRepository.delete(autor3);
    }
    
    
    @Test
    @DisplayName("Encontrar todos los autores paginando")
    void testFindAllWithPagination() {
        // Arrange
       

        // Definimos la paginación: página 0 (primera), 2 elementos por página
        Pageable pageable = PageRequest.of(0, 2);

        // Act
        Page<AutorEntity> page = autorRepository.findAll(pageable);

        // Assert
        assertNotNull(page);
        assertEquals(2, page.getContent().size(), "Debe devolver 2 autores en la primera página");
        assertEquals(3, page.getTotalElements(), "Debe haber un total de 3 autores");
        assertEquals(2, page.getSize(), "El tamaño de la página debe ser 2");
        assertEquals(0, page.getNumber(), "Debe ser la página 0");
    }
    
    
    @Test
    @DisplayName("Debe guardar un autor correctamente")
    void testSaveAutor() {
       
        AutorEntity autor = createAutorEntity(4L, "J.R.R.", "Tolkien");
       
        autorRepository.save(autor);
        Optional<AutorEntity> encontrado = autorRepository.findById(autor.getId().intValue());

        // Assert
        assertTrue(encontrado.isPresent(), "El autor debería haberse guardado");
        assertEquals(autor.getNombre(), encontrado.get().getNombre());
    }

    @Test
    @DisplayName("Debe eliminar un autor por ID")
    void testDeleteById() {
        // Arrange
    	AutorEntity autor = createAutorEntity(5L, "Isaac", "Asimov");
        
        autorRepository.save(autor);
  
        autorRepository.deleteById(autor.getId().intValue());
        Optional<AutorEntity> encontrado = autorRepository.findById(autor.getId().intValue());

        // Assert
        assertFalse(encontrado.isPresent(), "El autor debería haber sido eliminado");
    }

    @Test
    @DisplayName("Debe recuperar un autor por ID")
    void testFindByIdAutor() {
        
        // Act
        Optional<AutorEntity> encontrado = autorRepository.findById(1);

        // Assert
        assertTrue(encontrado.isPresent(), "El autor debería haberse encontrado");
        assertEquals(autor1.getNombre(), encontrado.get().getNombre());
    }
    
    
    private AutorEntity createAutorEntity(Long id, String nombre, String primerApellido) {
    	 
    	AutorEntity autor = new AutorEntity();
    	autor.setId(id);
    	autor.setNombre(nombre);
        autor.setPrimerApellido(primerApellido);
        return autor;
    }
}
