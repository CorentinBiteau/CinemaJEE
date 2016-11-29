package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.RealisateurService;
import dao.RealisateurService;
import exclusions.FilmExclusionStrategy;
import metier.Realisateur;
import metier.Realisateur;

import javax.ws.rs.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Vlad on 29/11/2016.
 */
@Path("/realisateurs")
public class RealisateurWService {
    @GET
    @Path("/all")
    @Produces("application/json")
    public String getRealisateurs() {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        RealisateurService realisateurService = new RealisateurService();
        List<Realisateur> realisateurs = realisateurService.getRealisateurs();
        return gson.toJson(realisateurs);
    }


    @GET
    @Path("/id/{realisateur_id}")
    @Produces("application/json")
    public String getRealisateur(@PathParam("realisateur_id") String RealisateurId) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        RealisateurService realisateurService = new RealisateurService();
        Realisateur realisateur = realisateurService.getRealisateurById(Integer.parseInt(RealisateurId));
        return gson.toJson(realisateur);
    }

    @GET
    @Path("/name/{realisateur_firstname}/{realisateur_surname}")
    @Produces("application/json")
    public String getRealisateurByName(@PathParam("realisateur_firstname") String RealisateurFirstName, @PathParam("realisateur_surname") String RealisateurSurname) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        RealisateurService realisateurService = new RealisateurService();
        Realisateur realisateur = realisateurService.getRealisateurByName(RealisateurFirstName, RealisateurSurname);
        return gson.toJson(realisateur);
    }

    @DELETE
    @Path("/delete/id/{realisateur_id}")
    @Produces("application/json")
    public String deleteRealisateur(@PathParam("realisateur_id") String RealisateurId) {
        RealisateurService realisateurService = new RealisateurService();
        int nbDeleted = realisateurService.deleteRealisateurById(Integer.parseInt(RealisateurId));
        return "{nbDeleted: " + nbDeleted + "}";
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public String AddRealisateur(@FormParam("nomAct") String RealisateurSurname,
                                 @FormParam("prenAct") String RealisateurFirstName) {

        Realisateur realisateur = new Realisateur();
        if (RealisateurFirstName != null) realisateur.setPrenRea(RealisateurFirstName);
        if (RealisateurSurname != null) realisateur.setNomRea(RealisateurSurname);

        RealisateurService realisateurService = new RealisateurService();

        realisateur.setNoRea((realisateurService.getMaxId().intValue() + 1));
        realisateurService.addRealisateur(realisateur);

        return new Gson().toJson(realisateur);
    }

    @PUT
    @Path("/update/id/{id}")
    @Produces("application/json")
    public String updateRealisateur(
            @PathParam("id") String RealisateurId,
            @FormParam("nomAct") String RealisateurSurname, @FormParam("prenAct") String RealisateurFirstName) {

        RealisateurService realisateurService = new RealisateurService();

        Realisateur realisateur = realisateurService.getRealisateurById(Integer.parseInt(RealisateurId));

        if (RealisateurFirstName != null) realisateur.setPrenRea(RealisateurFirstName);
        if (RealisateurSurname != null) realisateur.setNomRea(RealisateurSurname);

        realisateurService.updateRealisateur(realisateur);

        return new Gson().toJson(realisateur);
    }
}
