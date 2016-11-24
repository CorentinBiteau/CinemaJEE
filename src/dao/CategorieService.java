package dao;

import metier.Categorie;

import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class CategorieService extends EntityService {
    public List<Categorie> getCategories(){
        return (List<Categorie>) super.PerformQueryList("SELECT a FROM Categorie a ORDER BY a.libelleCat", null);
    }
}
