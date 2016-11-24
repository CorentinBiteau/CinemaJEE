package dao;

import metier.Acteur;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class ActeurService extends EntityService {
    public ActeurService() {
    }

    public List<Acteur> getActeurs() {
        return (List<Acteur>) super.PerformQueryList("SELECT a FROM Acteur a ORDER BY a.nomAct, a.prenAct", null);
    }

    public Acteur getActeurById(int ActeurId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", ActeurId);
        return (Acteur) super.PerformQuerySingle("SELECT a FROM Acteur a  WHERE a.noAct=:id", params);
    }

    public Acteur getActeurByName(String ActeurFirstName, String ActeurSurname) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("firstname", ActeurFirstName);
        params.put("surname", ActeurSurname);
        return (Acteur) super.PerformQuerySingle("SELECT a FROM Acteur a  WHERE a.nomAct=:surname AND a.prenAct=:firstname", params);
    }

    public int deleteActeurById(int ActeurId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", ActeurId);
        int a = super.PerformUpdate("DELETE FROM Personnage p WHERE p.acteur.noAct=:id", params);
        return a + super.PerformUpdate("DELETE FROM Acteur a  WHERE a.noAct=:id", params);
    }

    public Integer getMaxId() {
        return (Integer) super.PerformQuerySingle("SELECT MAX(a.noAct) from Acteur a", null);
    }


    public int deleteActeurByName(String ActeurFirstName, String ActeurSurname) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("firstname", ActeurFirstName);
        params.put("surname", ActeurSurname);
        return super.PerformUpdate("DELETE FROM Acteur a  WHERE a.nomAct=:surname AND a.prenAct=:firstname", params);
    }

    public void addActeur(Acteur acteur) {
        super.PerformInsert(acteur);
    }

    public int updateActeur(Acteur acteur) {
        HashMap<String, Object> params = new HashMap<>();

        params.put("id", acteur.getNoAct());
        params.put("firstname", acteur.getPrenAct());
        params.put("surname", acteur.getNomAct());
        params.put("birthdate", acteur.getDateNaiss());
        params.put("deathdate", acteur.getDateDeces());

        return super.PerformUpdate("UPDATE Acteur a SET " +
                "a.noAct=:id, a.nomAct=:surname, a.prenAct=:firstname, a.dateNaiss=:birthdate, a.dateDeces=:deathdate  " +
                "WHERE a.noAct=:id", params);
    }

}
