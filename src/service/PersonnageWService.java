package service;

/**
 * Created by Vlad on 29/11/2016.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.PersonnageService;
import exclusions.FilmExclusionStrategy;
import metier.Personnage;
import metier.PersonnagePK;

import javax.ws.rs.*;
import java.util.List;

@Path("/personnages")
public class PersonnageWService {
    @GET
    @Path("/all")
    @Produces("application/json")
    public String getPersonnages() {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        PersonnageService personnageService = new PersonnageService();
        List<Personnage> personnages = personnageService.getPersonnages();
        return gson.toJson(personnages);
    }


    @GET
    @Path("/movie/{title}")
    @Produces("application/json")
    public String getPersonnagesByMovieTitle(@PathParam("title") String movieTitle) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        PersonnageService personnageService = new PersonnageService();
        List<Personnage> personnages = personnageService.getPersonnagesByMovieTitle(movieTitle);
        return gson.toJson(personnages);
    }

    @DELETE
    @Path("/delete/name/{charName}")
    @Produces("application/json")
    public String deletePersonnage(@PathParam("charName") String charName) {
        PersonnageService personnageService = new PersonnageService();
        int nbDeleted = personnageService.deletePersonnageByName(charName);
        return "{nbDeleted: " + nbDeleted + "}";
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public String addPersonnage(@FormParam("noAct") String actorId, @FormParam("noFilm") String movieId,
                                @FormParam("nomPers") String charName
    ) {
        Personnage personnage = new Personnage();

        if (charName != null) personnage.setNomPers(charName);
        PersonnagePK personnagePK = new PersonnagePK();
        if (actorId != null) personnagePK.setNoAct(Integer.parseInt(actorId));
        if (movieId != null) personnagePK.setNoFilm(Integer.parseInt(movieId));
        personnage.setId(personnagePK);

        PersonnageService personnageService = new PersonnageService();
        personnageService.addPersonnage(personnage);
        return new Gson().toJson(personnage);
    }

    @PUT
    @Path("/update/movie/id/{movie_id}/actor/id/{act_id}")
    @Produces("application/json")
    public String updatePersonnageById(@PathParam("movie_id") String oldMovieId,
                                       @PathParam("act_id") String oldActorId,
                                       @FormParam("nomPers") String charName,
                                       @FormParam("noFilm") String newMovieId,
                                       @FormParam("noAct") String newActorId) {
        PersonnageService personnageService = new PersonnageService();

        Personnage oldPers = personnageService.getPersonnageById(oldMovieId, oldActorId);

        Personnage newPersonnage = new Personnage();
        if (charName != null) newPersonnage.setNomPers(charName);
        PersonnagePK personnagePK = new PersonnagePK();
        if (newActorId != null) personnagePK.setNoAct(Integer.parseInt(newActorId));
        if (newMovieId != null) personnagePK.setNoFilm(Integer.parseInt(newMovieId));
        newPersonnage.setId(personnagePK);


        personnageService.updatePersonnage(oldPers, newPersonnage);
        return new Gson().toJson(newPersonnage);
    }

    @PUT
    @Path("/update/name/{charName}")
    @Produces("application/json")
    public String updatePersonnageByName(@PathParam("charName") String oldCharName,
                                         @FormParam("nomPers") String charName,
                                         @FormParam("noFilm") String newMovieId,
                                         @FormParam("noAct") String newActorId) {
        PersonnageService personnageService = new PersonnageService();

        Personnage oldPers = personnageService.getPersonnageByName(charName);

        Personnage newPersonnage = new Personnage();
        if (charName != null) newPersonnage.setNomPers(charName);
        PersonnagePK personnagePK = new PersonnagePK();
        if (newActorId != null) personnagePK.setNoAct(Integer.parseInt(newActorId));
        if (newMovieId != null) personnagePK.setNoFilm(Integer.parseInt(newMovieId));
        newPersonnage.setId(personnagePK);


        personnageService.updatePersonnage(oldPers, newPersonnage);
        return new Gson().toJson(newPersonnage);
    }
}
