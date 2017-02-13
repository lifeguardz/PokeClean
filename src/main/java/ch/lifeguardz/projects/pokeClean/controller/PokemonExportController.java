package ch.lifeguardz.projects.pokeClean.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;

import ch.lifeguardz.projects.pokeClean.entities.PokeClean;
import ch.lifeguardz.projects.pokeClean.services.ExportService;

@Controller
public class PokemonExportController {

	@Resource
	private ExportService exportService;
	

    @RequestMapping(value = {"/app/pokemon/export", "/PokeClean/app/pokemon/export"}, method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

    	HttpSession session = request.getSession();
		if (session.getAttribute("PokeClean") == null) {
			return "redirect:/";
		}
		
		PokeClean pokeClean = (PokeClean) session.getAttribute("PokeClean");
        return "pogoclean/app/pokemon/export";
    }

   

    @RequestMapping(value = {"/app/pokemon/export", "/PokeClean/app/pokemon/export"}, method = RequestMethod.POST)
    public void download(Model model, HttpServletRequest request, HttpServletResponse response)
            throws IOException, LoginFailedException, RemoteServerException {
    	HttpSession session = request.getSession();
		PokeClean pokeClean = (PokeClean) session.getAttribute("PokeClean");
		exportService.saveToFile(response, pokeClean.getSortedPokemonList()); 
    }
}
