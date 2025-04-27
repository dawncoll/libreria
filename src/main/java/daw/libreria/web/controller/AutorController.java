package daw.libreria.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import daw.libreria.domain.model.Autor;
import daw.libreria.domain.service.AutorService;
import daw.libreria.persistence.pagination.PageHelper;

@Controller
@RequestMapping("/author")
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	@GetMapping("/list")
    public String listAuthors( @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending, Model theModel) {
       
        Pageable pageable = PageHelper.toPageable(page, size, sortBy, ascending);
        Page<Autor> autoresPg = autorService.findAll(pageable);
        theModel.addAttribute("autoresPage", autoresPg);
        
        //código mal puesto. A la larga se repetirá en distintos puntos. 
        //El alumnado debe detectarlo
        int totalPages = autoresPg.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            theModel.addAttribute("pageNumbers", pageNumbers);
        }
        
        return "listaAutores";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
      
        Autor theAuthor = new Autor();
        theModel.addAttribute("autor", theAuthor);
        return "autorForm";
    }


    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("authorId") Long theID, Model theModel) {
        
    	Optional<Autor> theAuthor = autorService.getAutor(theID);
    	if(theAuthor.isPresent()) {
    		theModel.addAttribute("autor", theAuthor.get());
    	}else {
    		//lalalalala
    	}
        return "author-form";
    }
    
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("autor") Autor theAuthor) {
      
    	autorService.save(theAuthor);
        return "redirect:/author/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("authorId") Long theId) {
        
    	autorService.deleteById(theId);
        return "redirect:/author/list";
    }
	
}
