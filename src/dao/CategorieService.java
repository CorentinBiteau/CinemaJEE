package dao;

import metier.Categorie;

import javax.persistence.EntityTransaction;

import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class CategorieService extends EntityService {
    public List<Categorie> getCategories(){
        List<Categorie> Categories = null;
        EntityTransaction transac = this.startTransaction();
        transac.begin();
        Categories = (List<Categorie>) entitymanager.createQuery("SELECT a FROM Categorie a ORDER BY a.libelleCat");
        entitymanager.close();
        emf.close();
        return Categories;
    }
}
