package dao;

import metier.Personnage;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class PersonnageService extends EntityService {
    public PersonnageService() {
    }

    public List<Personnage> getPersonnages(){
        return (List<Personnage>) super.PerformQueryList("SELECT a FROM Personnage a ORDER BY a.nomPers", null);
    }

    public List<Personnage> getPersonnagesByMovieTitle(String movieTitle){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", movieTitle);

        return (List<Personnage>) super.PerformQueryList("SELECT a FROM Personnage a WHERE a.film.titre=:name", params);
    }

    public Personnage getPersonnageById(String movieId, String ActorId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("movie_id", movieId);
        params.put("actor_id", ActorId);
        return (Personnage) super.PerformQuerySingle("SELECT p FROM Personnage p  WHERE p.id.noFilm=:movie_id AND p.id.noAct=:actor_id", params);
    }

    public Personnage getPersonnageByName(String catName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", catName);
        return (Personnage) super.PerformQuerySingle("SELECT p FROM Personnage p  WHERE p.nomPers=:name", params);
    }

    public int deletePersonnageByName(String Name){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", Name);
        return super.PerformUpdate("DELETE FROM Personnage p WHERE p.nomPers=:name", params);
    }

    public void addPersonnage(Personnage personnage ){
        super.PerformInsert(personnage);
    }


    public int updatePersonnage(Personnage oldPers, Personnage newPers){
        HashMap<String, Object> params = new HashMap<>();

        params.put("old_act_id", oldPers.getId().getNoAct() );
        params.put("old_film_id", oldPers.getId().getNoFilm() );
        params.put("act_id", newPers.getId().getNoFilm());
        params.put("film_id", newPers.getId().getNoFilm());
        params.put("name", newPers.getNomPers());

        return super.PerformUpdate("UPDATE Personnage p SET p.id.noFilm=:film_id, p.id.noAct=:act_id, p.nomPers=:name " +
                "WHERE p.id.noFilm=:old_film_id AND p.id.noAct=:old_act_id", params);
    }
}
