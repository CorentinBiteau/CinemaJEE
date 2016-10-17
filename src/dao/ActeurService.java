package dao;

import metier.Acteur;
import javax.persistence.EntityTransaction;

import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class ActeurService extends EntityService {
    public ActeurService() {
    }

    public List<Acteur> getActeurs(){
        List<Acteur> acteurs = null;
        EntityTransaction transac = this.startTransaction();
        transac.begin();
        acteurs = (List<Acteur>) entitymanager.createQuery("SELECT a FROM Acteur a ORDER BY a.nomAct, a.prenAct");
        entitymanager.close();
        emf.close();
        return acteurs;
    }
}
