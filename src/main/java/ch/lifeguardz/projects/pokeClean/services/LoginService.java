package ch.lifeguardz.projects.pokeClean.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.auth.GoogleUserCredentialProvider;
import com.pokegoapi.auth.PtcCredentialProvider;

import ch.lifeguardz.projects.pokeClean.entities.GoogleLogin;
import ch.lifeguardz.projects.pokeClean.entities.Login;
import ch.lifeguardz.projects.pokeClean.entities.PokeClean;
import ch.lifeguardz.projects.pokeClean.entities.PtcLogin;
import okhttp3.OkHttpClient;

@Service
public class LoginService {

	@Resource
	private PokemonService pokemonService;
	
	public PokeClean refreshPokeClean(PokeClean pokeClean) {
		OkHttpClient okHttpClient = new OkHttpClient();
		try {
			if (pokeClean.getLogin().isPtcLogin()) {
				String username = pokeClean.getLogin().getPtcLogin().getUsername();
				String password = pokeClean.getLogin().getPtcLogin().getPassword();
				PokemonGo go = new PokemonGo(new PtcCredentialProvider(okHttpClient, username, password), okHttpClient);
				pokeClean = new PokeClean(go.getPlayerProfile(), go.getInventories(), pokemonService.getSortedPokemonList(go.getInventories().getPokebank().getPokemons()), new Login(new PtcLogin(username, password)));
				return pokeClean;
			} else {
				String refreshToken = pokeClean.getLogin().getGoogleLogin().getRefreshToken();
				PokemonGo go = new PokemonGo(new GoogleUserCredentialProvider(okHttpClient, refreshToken), okHttpClient);
				pokeClean = new PokeClean(go.getPlayerProfile(), go.getInventories(), pokemonService.getSortedPokemonList(go.getInventories().getPokebank().getPokemons()), new Login(new GoogleLogin(refreshToken)));
				return pokeClean;
			}
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}
		return pokeClean;
	}
}
