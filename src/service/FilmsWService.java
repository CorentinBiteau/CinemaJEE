package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.FilmService;
import exclusions.FilmExclusionStrategy;
import metier.Film;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Vlad on 24/11/2016.
 */
@Path("/films")
public class FilmsWService {
    @GET
    @Path("/all")
    @Produces("application/json")
    public String getFilms(){
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        FilmService fs = new FilmService();
        List<Film> films= fs.getFilms();
        return gson.toJson(films);
    }


}
