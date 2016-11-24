package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CategorieService;
import exclusions.FilmExclusionStrategy;
import metier.Categorie;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Vlad on 24/11/2016.
 */
@Path("/categories")
public class CategorieWService {
    @GET
    @Path("/all")
    @Produces("application/json")
    public String getFilms() {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        CategorieService categorieService = new CategorieService();
        List<Categorie> categories = categorieService.getCategories();
        return gson.toJson(categories);
    }

    @GET
    @Path("/id/{cat_id}")
    @Produces("application/json")
    public String getFilm(@PathParam("cat_id") String catId) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        CategorieService fs = new CategorieService();
        Categorie f = fs.getCategorieById(catId);
        return gson.toJson(f);
    }


    @GET
    @Path("/name/{catName}")
    @Produces("application/json")
    public String getFilmByName(@PathParam("catName") String catName) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        CategorieService categorieService = new CategorieService();
        Categorie categorie = categorieService.getCategorieByName(catName);
        return gson.toJson(categorie);
    }

    @DELETE
    @Path("/delete/id/{CatId}")
    @Produces("application/json")
    public String deleteFilm(@PathParam("CatId") String CatId) {
        CategorieService categorieService = new CategorieService();
        int nbDeleted = categorieService.deleteCategorieById(CatId);
        return "{nbDeleted: " + nbDeleted + "}";
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public String addCategorie(@FormParam("codeCat") String codeCat, @FormParam("libelleCat") String libelleCat){
        Categorie categorie = new Categorie();
        categorie.setCodeCat(codeCat);
        categorie.setLibelleCat(libelleCat);

        CategorieService categorieService = new CategorieService();
        categorieService.addCategorie(categorie);
        return new Gson().toJson(categorie);
    }

    @PUT
    @Path("/update/id/{codeCat}")
    @Produces("application/json")
    public String addCategorie(@PathParam("codeCat") String oldCodeCat,
            @FormParam("codeCat") String NewcodeCat, @FormParam("libelleCat") String libelleCat){
        CategorieService categorieService = new CategorieService();

        Categorie oldCat = categorieService.getCategorieById(oldCodeCat);

        Categorie categorie = new Categorie();
        categorie.setCodeCat(NewcodeCat);
        categorie.setLibelleCat(libelleCat);

        categorieService.updateCategorie(oldCat, NewcodeCat, libelleCat);
        return new Gson().toJson(categorie);
    }
}
