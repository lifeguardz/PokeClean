package ch.lifeguardz.projects.pokeClean.entities;

import java.util.List;

import com.pokegoapi.api.inventory.Inventories;
import com.pokegoapi.api.player.PlayerProfile;

public class PokeClean {

	private PlayerProfile playerProfile;
	private Inventories inventories;
	private List<SortedPokemon> sortedPokemonList;
	private Login login;

	public PokeClean(PlayerProfile playerProfile, Inventories inventories,
			List<SortedPokemon> sortedPokemonList, Login login) {
		super();
		this.playerProfile = playerProfile;
		this.inventories = inventories;
		this.sortedPokemonList = sortedPokemonList;
		this.login = login;
	}

	public PlayerProfile getPlayerProfile() {
		return playerProfile;
	}

	public void setPlayerProfile(PlayerProfile playerProfile) {
		this.playerProfile = playerProfile;
	}

	public Inventories getInventories() {
		return inventories;
	}

	public void setInventories(Inventories inventories) {
		this.inventories = inventories;
	}

	public List<SortedPokemon> getSortedPokemonList() {
		return sortedPokemonList;
	}

	public void setSortedPokemonList(List<SortedPokemon> sortedPokemonList) {
		this.sortedPokemonList = sortedPokemonList;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}
