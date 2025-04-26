package daw.libreria.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import daw.libreria.domain.model.Libro;
import daw.libreria.domain.service.LibroService;

@Controller
@RequestMapping("/book")
public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping("/list")
    public String listBooks(Model theModel) {
        // get employees from db
        List<Libro> theBooks = libroService.findAll();
        // add to the spring model
        theModel.addAttribute("books", theBooks);
        return "list-books";
    }
	
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create model attribute to bind form data
        Libro theBook = new Libro();
        theModel.addAttribute("books", theBook);
        return "book-form";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") Long theID, Model theModel) {
        //get the book from the service
        Libro theBook = libroService.buscaPorId(theID);
        //set book as a model attribute to pre-populate the form
        theModel.addAttribute("books", theBook);
        return "book-form";
    }
    
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("books") Libro theBook) {
        // save the book
    	libroService.save(theBook);
        // use a redirect to prevent duplicate submissions
        return "redirect:/books/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {
        // delete the book
    	libroService.deleteById(theId);
        return "redirect:/books/list";
    }

    @GetMapping("/search")
    public String findBookByName(Model model, @Param("keyword") String keyword){
        model.addAttribute("books", libroService.buscaPorNombre(keyword));
        return "list-books";
    }
	
}
