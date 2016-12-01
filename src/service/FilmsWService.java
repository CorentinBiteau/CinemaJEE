package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CategorieService;
import dao.FilmService;
import dao.RealisateurService;
import exclusions.ActeurExclusionStrategy;
import exclusions.FilmExclusionStrategy;
import metier.Film;

import javax.ws.rs.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Vlad on 24/11/2016.
 */
@Path("/films")
public class FilmsWService {

    @GET
    @Path("/all")
    @Produces("application/json")
    public String getFilms() {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        FilmService fs = new FilmService();
        List<Film> films = fs.getFilms();
        return gson.toJson(films);
    }

    @GET
    @Path("/id/{film_id}")
    @Produces("application/json")
    public String getFilm(@PathParam("film_id") String filmId) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create();
        FilmService fs = new FilmService();
        Film f = fs.getFilmById(Integer.parseInt(filmId));
        return gson.toJson(f);
    }

    @GET
    @Path("/name/{moviename}")
    @Produces("application/json")
    public String getFilmByName(@PathParam("moviename") String MovieName) {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ActeurExclusionStrategy()).create();
        FilmService fs = new FilmService();
        Film film = fs.getFilmByName(MovieName);
        return gson.toJson(film);
    }

    @DELETE
    @Path("/delete/id/{film_id}")
    @Produces("application/json")
    public String deleteFilm(@PathParam("film_id") String filmId) {
        FilmService filmService = new FilmService();
        int nbDeleted = filmService.deleteFilmById(Integer.parseInt(filmId));
        return "{nbDeleted: " + nbDeleted + "}";
    }

    @POST
    @Path("/add")
    @Produces("application/json")
    public String addFilm(@FormParam("titre") String MovieTitle, @FormParam("duree") String MovieDuration,
                          @FormParam("dateSortie") String MovieReleaseDate, @FormParam("budget") String MovieBudget,
                          @FormParam("noRea") String RealisateurId, @FormParam("codeCat") String categoryId,
                          @FormParam("montantRecette") String MovieMontantRecette) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Film f = new Film();

        if (MovieTitle != null) f.setTitre(MovieTitle);
        if (MovieDuration != null) f.setDuree(Integer.parseInt(MovieDuration));
        if (MovieReleaseDate != null) try {
            f.setDateSortie(formatter.parse(MovieReleaseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (MovieBudget != null) f.setBudget(Integer.parseInt(MovieBudget));
        if (MovieMontantRecette != null) f.setMontantRecette(Integer.parseInt(MovieMontantRecette));
        if (categoryId != null) f.setCategorie(new CategorieService().getCategorieById(categoryId));
        if (RealisateurId != null)
            f.setRealisateur(new RealisateurService().getRealisateurById(Integer.parseInt(RealisateurId)));

        FilmService fs = new FilmService();
        f.setNoFilm(fs.getMaxId().intValue() + 1);
        fs.addFilm(f);

        return new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create().toJson(f);
    }

    @PUT
    @Path("/update/id/{old_movie_id}")
    @Produces("application/json")
    public String updateFilm(
            @PathParam("old_movie_id") String oldFilmId,
            @FormParam("noFilm") String newFilmId,
            @FormParam("titre") String MovieTitle, @FormParam("duree") String MovieDuration,
            @FormParam("dateSortie") String MovieReleaseDate, @FormParam("budget") String MovieBudget,
            @FormParam("montantRecette") String MovieMontantRecette) {

        FilmService as = new FilmService();

        Film oldFilm = as.getFilmById(Integer.parseInt(oldFilmId));
        Film newFilm = as.getFilmById(Integer.parseInt(oldFilmId));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (newFilmId != null) newFilm.setNoFilm(Integer.parseInt(newFilmId));
        if (MovieTitle != null) newFilm.setTitre(MovieTitle);
        if (MovieDuration != null) newFilm.setDuree(Integer.parseInt(MovieDuration));
        if (MovieReleaseDate != null) try {
            newFilm.setDateSortie(formatter.parse(MovieReleaseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (MovieBudget != null) newFilm.setBudget(Integer.parseInt(MovieBudget));
        if (MovieMontantRecette != null) newFilm.setMontantRecette(Integer.parseInt(MovieMontantRecette));

        as.updateFilm(oldFilm, newFilm);

        return new GsonBuilder().addSerializationExclusionStrategy(new FilmExclusionStrategy()).create().toJson(newFilm);
    }
}
