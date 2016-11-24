package dao;

import metier.Categorie;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class CategorieService extends EntityService {
    public List<Categorie> getCategories(){
        return (List<Categorie>) super.PerformQueryList("SELECT a FROM Categorie a ORDER BY a.libelleCat", null);
    }

    public Categorie getCategorieById(String catId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", catId);
        return (Categorie) super.PerformQuerySingle("SELECT a FROM Categorie a  WHERE a.codeCat=:id", params);
    }

    public Categorie getCategorieByName(String catName) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", catName);
        return (Categorie) super.PerformQuerySingle("SELECT a FROM Categorie a  WHERE a.libelleCat=:name", params);
    }

    public int deleteCategorieById(String catId){
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", catId);
        int a = super.PerformUpdate("DELETE FROM Film f WHERE f.categorie.codeCat=:id", params);
        return a + super.PerformUpdate("DELETE FROM Categorie c WHERE c.codeCat=:id", params);
    }

    public int deleteCategorieByName(String Name){
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", Name);
        int a = super.PerformUpdate("DELETE FROM Film f WHERE f.categorie.libelleCat=:name", params);
        return a + super.PerformUpdate("DELETE FROM Categorie c WHERE c.libelleCat=:name", params);
    }

    public void addCategorie(Categorie categorie ){
        super.PerformInsert(categorie);
    }

    public int updateCategorie(Categorie oldCat, String newCode, String newName){
        HashMap<String, Object> params = new HashMap<>();

        params.put("oldid", oldCat.getCodeCat() );
        params.put("id", newCode);
        params.put("name", newName);

        return super.PerformUpdate("UPDATE Categorie c SET c.codeCat=:id, c.libelleCat=:name " +
                "WHERE c.codeCat=:oldid", params);
    }
}
