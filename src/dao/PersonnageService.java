package dao;

import metier.Personnage;

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
}
