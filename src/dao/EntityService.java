package dao;

import metier.Acteur;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

public abstract class EntityService {
    private EntityManager entitymanager;
    private EntityManagerFactory emf;

    private EntityTransaction startTransaction() {
        emf = Persistence.createEntityManagerFactory("cinema");
        entitymanager = emf.createEntityManager();
        return entitymanager.getTransaction();
    }

    protected List PerformQueryList(String Statement, HashMap<String, Object> Params) {
        List objects = null;
        openConnection();
        Query query = buildQuery(Statement, Params);
        objects = query.getResultList();
        closeConnection();
        return objects;
    }

    protected Object PerformQuerySingle(String Statement, HashMap<String, Object> Params){
        Object o;
        openConnection();
        Query query = buildQuery(Statement, Params);
        o = query.getSingleResult();
        closeConnection();
        return o;
    }

    protected int PerformUpdate(String Statement, HashMap<String, Object> Params) {
        openConnection();
        Query query = buildQuery(Statement, Params);
        int a = query.executeUpdate();
        entitymanager.getTransaction().commit();
        closeConnection();
        return a;
    }

    protected void PerformInsert(Object o){
        openConnection();
        entitymanager.persist(o);
        entitymanager.getTransaction().commit();
        closeConnection();
    }

    private Query buildQuery(String Statement, HashMap<String, Object> Params) {
        Query query = entitymanager.createQuery(Statement);
        if (Params != null) {
            if (!Params.isEmpty())
                for (String s : Params.keySet()) {
                    query.setParameter(s, Params.get(s));
                }
        }
        return query;
    }

    private void closeConnection() {
        entitymanager.close();
        emf.close();
    }

    private void openConnection() {
        EntityTransaction transac = this.startTransaction();
        transac.begin();
    }

}