package ch.lifeguardz.projects.pokeClean.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = { "/app/home", "/PokeClean/app/home" }, method = RequestMethod.GET)
	public String home() {
		return "redirect:/app/pokemon";
	}
}
