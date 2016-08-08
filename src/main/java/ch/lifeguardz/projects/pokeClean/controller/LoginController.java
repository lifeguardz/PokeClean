package ch.lifeguardz.projects.pokeClean.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.auth.GoogleUserCredentialProvider;
import com.pokegoapi.auth.PtcCredentialProvider;

import ch.lifeguardz.projects.pokeClean.entities.GoogleLogin;
import ch.lifeguardz.projects.pokeClean.entities.Login;
import ch.lifeguardz.projects.pokeClean.entities.PokeClean;
import ch.lifeguardz.projects.pokeClean.entities.PtcLogin;
import ch.lifeguardz.projects.pokeClean.forms.LoginForm;
import ch.lifeguardz.projects.pokeClean.services.PokemonService;
import okhttp3.OkHttpClient;

@Controller
public class LoginController {

	@Resource
	private PokemonService pokemonService;

	@RequestMapping(value = {"/", "/PokeClean"}, method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		HttpSession session = request.getSession();
		if (session.getAttribute("PokeClean") != null) {
			return "redirect:/app/pokemon";
		}

		return "pogoclean/app/login/login";
	}

	@RequestMapping(value = {"/", "/PokeClean"}, method = RequestMethod.POST)
	public String loging(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes, @ModelAttribute("loginForm") LoginForm loginForm) {

		HttpSession session = request.getSession();
		if (session.getAttribute("PokeClean") == null) {
			// add pokeclean infos into session
			OkHttpClient okHttpClient = new OkHttpClient();
			try {
				if(!(loginForm.getUsername().isEmpty()  || loginForm.getPassword().isEmpty())) {
				PokemonGo go = new PokemonGo(new PtcCredentialProvider(okHttpClient, loginForm.getUsername(), loginForm.getPassword()), okHttpClient);
				PokeClean pokeClean = new PokeClean(go.getPlayerProfile(), go.getInventories(), pokemonService.getSortedPokemonList(go.getInventories().getPokebank().getPokemons()), new Login(new PtcLogin(loginForm.getUsername(), loginForm.getPassword())));
				session.setAttribute("PokeClean", pokeClean);
				} else if (!loginForm.getGoogleCode().isEmpty()) {
					GoogleUserCredentialProvider provider = new GoogleUserCredentialProvider(okHttpClient);
					provider.login(loginForm.getGoogleCode());
					PokemonGo go = new PokemonGo(provider, okHttpClient);
					PokeClean pokeClean = new PokeClean(go.getPlayerProfile(), go.getInventories(), pokemonService.getSortedPokemonList(go.getInventories().getPokebank().getPokemons()), new Login(new GoogleLogin(provider.getRefreshToken())));
					session.setAttribute("PokeClean", pokeClean);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				redirectAttributes.addFlashAttribute("error", "Login server down. Please try in a few minutes again.");
				return "redirect:/";
			}			
		} else {
			return "redirect:/app/pokemon";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = {"/login/google", "/PokeClean/login/google"},method = RequestMethod.GET)
	public String logGoogleRedirect(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		return "redirect:" + GoogleUserCredentialProvider.LOGIN_URL;
	}
	
	@RequestMapping(value = {"/logout", "/PokeClean/logout"},method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		HttpSession session = request.getSession();
		if (session.getAttribute("PokeClean") != null) {
			session.setAttribute("PokeClean", null);
			redirectAttributes.addFlashAttribute("success", "You have been successfully logged out!");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = {"/faq", "/PokeClean/faq"},method = RequestMethod.GET)
	public String faq(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		return "pogoclean/app/others/faq";
	}
	
	@RequestMapping(value = {"/contact", "/PokeClean/contact"},method = RequestMethod.GET)
	public String contact(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		return "pogoclean/app/others/contact";
	}
	
}