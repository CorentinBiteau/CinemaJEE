package dao;

import metier.Realisateur;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Vlad on 17/10/2016.
 */
public class RealisateurService extends EntityService {
    public RealisateurService() {
    }

    public List<Realisateur> getRealisateurs() {
        return (List<Realisateur>) super.PerformQueryList("SELECT r FROM Realisateur r ORDER BY r.nomRea, r.prenRea", null);
    }

    public Realisateur getRealisateurById(int RealisateurId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", RealisateurId);
        return (Realisateur) super.PerformQuerySingle("SELECT r FROM Realisateur r  WHERE r.noRea=:id", params);
    }

    public Realisateur getRealisateurByName(String RealisateurFirstName, String RealisateurSurname) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("firstname", RealisateurFirstName);
        params.put("surname", RealisateurSurname);
        return (Realisateur) super.PerformQuerySingle("SELECT r FROM Realisateur r  WHERE r.nomRea=:surname AND r.prenRea=:firstname", params);
    }

    public int deleteRealisateurById(int RealisateurId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", RealisateurId);
        int a = super.PerformUpdate("DELETE FROM Film f WHERE f.realisateur.noRea=:id", params);
        return a + super.PerformUpdate("DELETE FROM Realisateur r  WHERE r.noRea=:id", params);
    }

    public int deleteRealisateurByName(String RealisateurFirstName, String RealisateurSurname) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("firstname", RealisateurFirstName);
        params.put("surname", RealisateurSurname);
        int a = super.PerformUpdate("DELETE FROM Film f WHERE f.realisateur.nomRea=:surname AND f.realisateur.prenRea=:firstname", params);
        return super.PerformUpdate("DELETE FROM Realisateur r  WHERE r.nomRea=:surname AND r.prenRea=:firstname", params);
    }

    public Integer getMaxId() {
        return (Integer) super.PerformQuerySingle("SELECT MAX(r.noRea) from Realisateur r", null);
    }

    public void addRealisateur(Realisateur realisateur) {
        super.PerformInsert(realisateur);
    }

    public int updateRealisateur(Realisateur realisateur) {
        HashMap<String, Object> params = new HashMap<>();

        params.put("id", realisateur.getNoRea());
        params.put("firstname", realisateur.getPrenRea());
        params.put("surname", realisateur.getNomRea());

        return super.PerformUpdate("UPDATE Realisateur r SET " +
                "r.noRea=:id, r.nomRea=:surname, r.prenRea=:firstname  " +
                "WHERE r.noRea=:id", params);
    }


}
