package ch.lifeguardz.projects.pokeClean.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.pokegoapi.api.pokemon.Pokemon;

import ch.lifeguardz.projects.pokeClean.entities.PokeClean;
import ch.lifeguardz.projects.pokeClean.entities.SortedPokemon;

@Service
public class PokemonService {

	public List<SortedPokemon> getSortedPokemonList(List<Pokemon> pokemonList) {
		List<SortedPokemon> sortedPokemonList = new ArrayList<>();
		TreeMap<Integer, Pokemon> treeMap = new TreeMap<Integer, Pokemon>();

		for (Pokemon pok : pokemonList) {
			treeMap.put(pok.getPokemonId().getNumber(), pok);
		}

		for (Pokemon pok : treeMap.values()) {
			List<Pokemon> samePokemons = new ArrayList<>();
			for (Pokemon pokemon : pokemonList) {
				if (pokemon.getPokemonId().getNumber() == pok.getPokemonId().getNumber()) {
					samePokemons.add(pokemon);
				}
			}
			sortedPokemonList.add(new SortedPokemon(pok, samePokemons));
		}

		return sortedPokemonList;
	}
	
	public SortedPokemon getSortedPokemonById(int id, PokeClean pokeClean) {
		SortedPokemon sortedPokemon = null;
		for (SortedPokemon srtdPok : pokeClean.getSortedPokemonList()) {
			if (srtdPok.getPokemon().getPokemonId().getNumber() == id) {
				sortedPokemon = srtdPok;
			}
		}
		return sortedPokemon;
	}
	
	public boolean tranferOnePokemonById(Pokemon pokemon) {
		try {
			pokemon.transferPokemon();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean tranferOnePokemon(String[] longIdList, PokeClean pokeClean) {
		Random rnd = new Random();
		try {
		for (String longId : longIdList) {
			pokeClean
			.getInventories()
			.getPokebank()
			.getPokemonById(
					Long.valueOf(
							longId
							)
					)
			.transferPokemon();
			Thread.sleep(300 + rnd.nextInt(2000 - 500 + 1));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
}
