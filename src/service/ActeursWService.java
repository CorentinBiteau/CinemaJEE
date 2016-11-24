package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import metier.Acteur;

import javax.ws.rs.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.ActeurService;
import exclusions.ActeurExclusionStrategy;

@Path("/acteurs")
public class ActeursWService {

    @GET
    @Path("/all")
    @Produces("application/json")
    public String getActeurs() {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ActeurExclusionStrategy()).create();
        ActeurService as = new ActeurService();
        List<Acteur> acteurs = as.getActeurs();
        return gson.toJson(acteurs);
    }


    @GET
    @Path("/id/{acteur_id}")
    @Produces("application/json")
    public String getActeur(@PathParam("acteur_id") String ActeurId) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ActeurExclusionStrategy()).create();
        ActeurService as = new ActeurService();
        Acteur acteur = as.getActeurById(Integer.parseInt(ActeurId));
        return gson.toJson(acteur);
    }

    @GET
    @Path("/name/{acteur_firstname}/{acteur_surname}")
    @Produces("application/json")
    public String getActeurByName(@PathParam("acteur_firstname") String ActeurFirstName, @PathParam("acteur_surname") String ActeurSurname) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ActeurExclusionStrategy()).create();
        ActeurService as = new ActeurService();
        Acteur acteur = as.getActeurByName(ActeurFirstName, ActeurSurname);
        return gson.toJson(acteur);
    }


    @DELETE
    @Path("/delete/id/{acteur_id}")
    @Produces("application/json")
    public String deleteActeur(@PathParam("acteur_id") String ActeurId) {
        ActeurService as = new ActeurService();
        int nbDeleted = as.deleteActeurById(Integer.parseInt(ActeurId));
        return "{nbDeleted: " + nbDeleted + "}";
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public String AddActeur(@FormParam("nomAct") String ActeurSurname, @FormParam("prenAct") String ActeurFirstName,
                            @FormParam("dateNaiss") String BirthDate, @FormParam("dateDeces") String DeathDate) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Acteur a = new Acteur();
        if (ActeurFirstName != null) a.setPrenAct(ActeurFirstName);
        if (ActeurSurname != null) a.setNomAct(ActeurSurname);

        if (BirthDate != null) try {
            a.setDateNaiss(formatter.parse(BirthDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (DeathDate != null) try {
            a.setDateDeces(formatter.parse(DeathDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ActeurService as = new ActeurService();

        a.setNoAct((as.getMaxId().intValue() + 1));
        as.addActeur(a);
        return new Gson().toJson(a);
    }


    @PUT
    @Path("/update/id/{id}")
    @Produces("application/json")
    public String updateActeur(
            @PathParam("id") String ActeurId,
            @FormParam("nomAct") String ActeurSurname, @FormParam("prenAct") String ActeurFirstName,
            @FormParam("dateNaiss") String BirthDate, @FormParam("dateDeces") String DeathDate) {

        ActeurService as = new ActeurService();

        Acteur a = as.getActeurById(Integer.parseInt(ActeurId));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (ActeurFirstName != null) a.setPrenAct(ActeurFirstName);
        if (ActeurSurname != null) a.setNomAct(ActeurSurname);

        if (BirthDate != null) try {
            a.setDateNaiss(formatter.parse(BirthDate))  ;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (DeathDate != null) try {
            a.setDateDeces(formatter.parse(DeathDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        as.updateActeur(a);

        return new Gson().toJson(a);
    }


}
