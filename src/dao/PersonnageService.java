package dao;

import metier.Personnage;

import javax.persistence.EntityTransaction;

import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class PersonnageService extends EntityService {
    public PersonnageService() {
    }

    public List<Personnage> getPersonnages(){
        List<Personnage> Personnages = null;
        EntityTransaction transac = this.startTransaction();
        transac.begin();
        Personnages = (List<Personnage>) entitymanager.createQuery("SELECT a FROM Personnage a ORDER BY a.nomPers");
        entitymanager.close();
        emf.close();
        return Personnages;
    }
}
