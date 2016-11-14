package service;

import java.util.List;
import metier.Acteur;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ActeurService;
import exclusions.ActeurExclusionStrategy;

@Path("/cinema")
public class WService {
	
	@GET
	@Path("/acteurs")
	@Produces("application/json")
	public String getActeurs(){
		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ActeurExclusionStrategy()).create();
		ActeurService as = new ActeurService();
		List<Acteur> acteurs = as.getActeurs();
		return gson.toJson(acteurs);
	}

	@GET
	@Path("/test")
	@Produces("application/json")
	public String test(){
		return "Alright";
	}
}
