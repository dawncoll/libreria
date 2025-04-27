package daw.libreria.web.controller;

import daw.libreria.domain.model.Autor;
import daw.libreria.domain.service.AutorService;
import daw.libreria.persistence.pagination.PageHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutorControllerTest {

    @Mock
    private AutorService autorService;

    @Mock
    private Model model;

    @InjectMocks
    private AutorController autorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe devolver la vista de listado de autores")
    void testListAuthors() {
        
  
    	Page<Autor> autorsPg = PageHelper.toPage(List.of(new Autor(), new Autor()));
        when(autorService.findAll(Mockito.any())).thenReturn(autorsPg);

        // Act
        String viewName = autorController.listAuthors(0, 5, "id",true, model);

        // Assert
        assertEquals("listaAutores", viewName);
        verify(model, times(1)).addAttribute("autoresPage", autorsPg);
        verify(autorService, times(1)).findAll(Mockito.any());
    }

    @Test
    @DisplayName("Debe devolver la vista del formulario para añadir un autor")
    void testShowFormForAdd() {
        // Act
        String viewName = autorController.showFormForAdd(model);

        // Assert
        assertEquals("autorForm", viewName);
        verify(model, times(1)).addAttribute(eq("autor"), Mockito.any(Autor.class));
    }
}

