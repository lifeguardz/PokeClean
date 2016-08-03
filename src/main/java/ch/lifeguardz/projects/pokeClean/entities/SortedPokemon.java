package ch.lifeguardz.projects.pokeClean.entities;

import java.util.List;

import com.pokegoapi.api.pokemon.Pokemon;

public class SortedPokemon {

	private Pokemon pokemon;
	private List<Pokemon> pokemonList;

	public SortedPokemon(Pokemon pokemon, List<Pokemon> pokemonList) {
		super();
		this.pokemon = pokemon;
		this.pokemonList = pokemonList;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public List<Pokemon> getPokemonList() {
		return pokemonList;
	}

	public void setPokemonList(List<Pokemon> pokemonList) {
		this.pokemonList = pokemonList;
	}

}
