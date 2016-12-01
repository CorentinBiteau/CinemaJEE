package dao;

import metier.Film;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class FilmService extends EntityService {
    public FilmService() {
    }

    public List<Film> getFilms() {
        String statement = "SELECT a FROM Film a ORDER BY a.noFilm";
        return (List<Film>) super.PerformQueryList(statement, null);
    }

    public Film getFilmById(int FilmId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", FilmId);
        return (Film) super.PerformQuerySingle("SELECT a FROM Film a  WHERE a.noFilm=:id", params);
    }

    public Film getFilmByName(String FilmName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", FilmName);
        return (Film) super.PerformQuerySingle("SELECT a FROM Film a  WHERE a.titre=:name", params);
    }

    public int deleteFilmById(int Filmid){
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", Filmid);
        int a = super.PerformUpdate("DELETE FROM Personnage p WHERE p.film.noFilm=:id", params);
        return a + super.PerformUpdate("DELETE FROM Film f WHERE f.noFilm=:id", params);
    }

    public int deleteFilmByName(String Name){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", Name);
        int a = super.PerformUpdate("DELETE FROM Personnage p WHERE p.film.noFilm=:id", params);
        return a + super.PerformUpdate("DELETE FROM Film f WHERE f.titre=:name", params);
    }

    public void addFilm(Film f){
        super.PerformInsert(f);
    }

    public int updateFilm(Film oldFilm, Film newFilm){
        HashMap<String, Object> params = new HashMap<>();

        params.put("old_id", oldFilm.getNoFilm());
        params.put("id", newFilm.getNoFilm());
        params.put("name", newFilm.getTitre());
        params.put("duration", newFilm.getDuree());
        params.put("releasedate", newFilm.getDateSortie());
        params.put("budget", newFilm.getBudget());
        params.put("earnings", newFilm.getMontantRecette());

        return super.PerformUpdate("UPDATE Film f SET " +
                "f.noFilm=:id, f.titre=:name, f.duree=:duration, f.dateSortie=:releasedate, f.budget=:budget, f.montantRecette=:earnings " +
                "WHERE f.noFilm=:old_id", params);

    }

    public Integer getMaxId() {
        return (Integer) super.PerformQuerySingle("SELECT MAX(a.noFilm) from Film a", null);
    }


}
