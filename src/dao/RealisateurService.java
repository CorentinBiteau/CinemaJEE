package dao;

import metier.Realisateur;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class RealisateurService extends EntityService{
    public RealisateurService() {
    }

    public List<Realisateur> getRealisateurs(){
        List<Realisateur> Realisateurs = null;
        EntityTransaction transac = this.startTransaction();
        transac.begin();
        Realisateurs = (List<Realisateur>) entitymanager.createQuery("SELECT a FROM Realisateur a ORDER BY a.nomRea, a.prenRea");
        entitymanager.close();
        emf.close();
        return Realisateurs;
    }
}
