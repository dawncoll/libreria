package daw.libreria.domain.mapper;

import static org.junit.jupiter.api.Assertions.*;

import daw.libreria.persistence.entity.AutorEntity;
import daw.libreria.domain.model.Autor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

class AutorMapperUnitTest {

    private AutorMapper autorMapper;

    @BeforeEach
    void setUp() {
        autorMapper = new AutorMapper();
    }

    @Test
    @DisplayName("Debe convertir correctamente una Page de AutorEntity en una Page de AutorDto")
    void testToPage() {
        // Arrange
        AutorEntity autor1 = new AutorEntity();
        autor1.setId(1L);
        autor1.setNombre("Gabriel García Márquez");

        AutorEntity autor2 = new AutorEntity();
        autor2.setId(2L);
        autor2.setNombre("Isabel Allende");

        List<AutorEntity> autores = List.of(autor1, autor2);
        Page<AutorEntity> pageAutorEntity = new PageImpl<>(autores, PageRequest.of(0, 10), autores.size());

        // Act
        Page<Autor> pageAutorDto = autorMapper.fromAutorEntityPgToAutorPg(pageAutorEntity);

        // Assert
        assertNotNull(pageAutorDto);
        assertEquals(2, pageAutorDto.getTotalElements());
        assertEquals("Gabriel García Márquez", pageAutorDto.getContent().get(0).getNombre());
        assertEquals("Isabel Allende", pageAutorDto.getContent().get(1).getNombre());
    }
}
