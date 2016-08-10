package ch.lifeguardz.projects.pokeClean.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.lifeguardz.projects.pokeClean.entities.PokeClean;
import ch.lifeguardz.projects.pokeClean.entities.PokemonTransfer;
import ch.lifeguardz.projects.pokeClean.services.LoginService;
import ch.lifeguardz.projects.pokeClean.services.PokemonService;

@RestController
public class PokemonRestController {
	
	@Resource
	private PokemonService pokemonService;
	
	@Resource
	private LoginService loginService;
	
	@RequestMapping(value = {"/app/pokemon/transfer", "/PokeClean/app/pokemon/transfer"}, method = RequestMethod.GET)
	public PokemonTransfer transfer(Model model, @RequestParam("pokemonTransfer") String pokemonLongArray, HttpServletRequest request){
		PokemonTransfer pokemonTransfer = new PokemonTransfer(1);
		if (pokemonLongArray.isEmpty()) {
			return null;
		}
		
		HttpSession session = request.getSession();
		PokeClean pokeClean = (PokeClean) session.getAttribute("PokeClean");
		if(!pokemonService.tranferPokemonByStringArray(StringUtils.delimitedListToStringArray(pokemonLongArray, ","), pokeClean)) {
			return null;
		}
		pokeClean = loginService.refreshPokeClean(pokeClean);
		session.setAttribute("PokeClean", pokeClean);
		return pokemonTransfer;
	}
}
