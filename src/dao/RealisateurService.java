package dao;

import metier.Realisateur;

import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class RealisateurService extends EntityService{
    public RealisateurService() {
    }

    public List<Realisateur> getRealisateurs(){
        return  (List<Realisateur>) super.PerformQueryList("SELECT a FROM Realisateur a ORDER BY a.nomRea, a.prenRea", null);
    }
}
