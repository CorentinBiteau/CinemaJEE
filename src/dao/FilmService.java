package dao;

import metier.Film;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class FilmService extends EntityService{
    public FilmService() {
    }

    public List<Film> getFilms(){
        String statement = "SELECT a FROM Film a ORDER BY a.id";
        return (List<Film>) super.PerformQueryList(statement, null);
    }

    public List<Film> getFilm(int FilmId){
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", FilmId);
        return (List<Film>) super.PerformQueryList("SELECT a FROM Film a  WHERE a.id=:id ORDER BY a.id", params);
    }
}
