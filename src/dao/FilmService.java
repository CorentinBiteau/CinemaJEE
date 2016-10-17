package dao;

import metier.Film;

import javax.persistence.EntityTransaction;

import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class FilmService extends EntityService{
    public FilmService() {
    }

    public List<Film> getFilms(){
        List<Film> Films = null;
        EntityTransaction transac = this.startTransaction();
        transac.begin();
        Films = (List<Film>) entitymanager.createQuery("SELECT a FROM Film a ORDER BY a.id");
        entitymanager.close();
        emf.close();
        return Films;
    }
}
