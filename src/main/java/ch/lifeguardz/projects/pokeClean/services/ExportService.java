package ch.lifeguardz.projects.pokeClean.services;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.pokegoapi.api.pokemon.Pokemon;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;

import ch.lifeguardz.projects.pokeClean.entities.SortedPokemon;

@Service
public class ExportService {
	
    public void saveToFile(HttpServletResponse response, List<SortedPokemon> pokemonList)
    		throws IOException, LoginFailedException, RemoteServerException {
        StringBuilder sb = new StringBuilder();
        
            sb.append("Nr.;Pokémon;Count;Candy\n");
            for (SortedPokemon p : pokemonList) {
                sb.append(p.getPokemon().getPokemonId().getNumber());
                sb.append(";");
                sb.append(p.getPokemon().getPokemonId());
                sb.append(";");
                sb.append(p.getPokemonList().size());
                sb.append(";");
                sb.append(p.getPokemon().getCandy());
                sb.append(";");
                sb.append("\n");
            }
            
            sb.append("\n\n");
            for (SortedPokemon p : pokemonList) {
            	sb.append(p.getPokemon().getPokemonId().getNumber() + 
                 		" - " + 
                 		p.getPokemon().getPokemonId() + "\n");
            	sb.append("Nickname;CP;Weight (kg);Height (m);IV Score; Favorite\n");
            	for (Pokemon po : p.getPokemonList()) {
	            	sb.append(po.getNickname());
	                sb.append(";");
	                sb.append(po.getCp());
	                sb.append(";");
	                sb.append(po.getWeightKg());
	                sb.append(";");
	                sb.append(po.getHeightM());
	                sb.append(";");
	                sb.append(po.getIvRatio());
	                sb.append(";");
	                sb.append(po.getFavorite());
	                sb.append(";");
	                sb.append("\n");
            	}
            	sb.append("\n");
            }
        
        String strExport = sb.toString();
        String filenameToSave = "pokemon_export.csv";
        OutputStream os = response.getOutputStream();
        prepareResponseFor(response, filenameToSave);
        streamFileTo(response, strExport, os);
        os.flush();
    }

    private void prepareResponseFor(HttpServletResponse response, String filenameToSave) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;      filename=\"" + filenameToSave + "\"");
    }

	private void streamFileTo(HttpServletResponse response, String strExport, OutputStream os) {
		try {
			int i = 0;
			while (i < strExport.length()) {
				os.write(strExport.charAt(i));
				i++;
			}
		} catch (IOException e) {

		}
	}

}
