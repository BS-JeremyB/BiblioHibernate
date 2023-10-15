package be.bstorm.repositories.impl;

import be.bstorm.repositories.CRUDRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class CRUDRepositoryImpl <T> implements CRUDRepository<T> {

    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private final String tableName;
    private final Class<T> entityType;

    public CRUDRepositoryImpl(String tableName, Class<T> entityType) {
        this.tableName = tableName;
        this.entityType = entityType;


    }
    @Override
    public List<T> getALL() {

        Session s = sf.openSession();

        try {
            // Methode utilisant la généricité pour recupérer tous les éléments d'une classe T
            String queryString = "from " + tableName;
            // Utilisation d'une requete HQL préparé ci-dessus
            Query<T> query = s.createQuery(queryString, this.entityType);
            return query.list();
        } finally {
            s.close();
        }

    }

    @Override
    public T getById(long id) {

        Session s = sf.openSession();

        try {
            // Methode utilisant la généricité pour recupérer un élément d'une classe T
            return s.get(entityType,id);
        } finally {
            s.close();
        }
    }

    @Override
    public void create(T element) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            // Methode utilisant la généricité pour créer un élément d'une classe T
            tx = s.beginTransaction();
            s.persist(element);
            tx.commit();
        } finally {
            s.close();
        }
    }

    @Override
    public T update(long id, T element) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            // Methode utilisant la généricité pour mettre à jour un élément d'une classe T

            // Recherche de l'élément à modifier
            T elementToUpdate = s.get(entityType, id);

            if (elementToUpdate != null) {

                //Remplacement de l'élément à modifier par un élément mit à jour
                elementToUpdate = element;
                tx = s.beginTransaction();
                s.update(elementToUpdate);
                tx.commit();
                return  elementToUpdate;
            }
        } finally {
            s.close();
        }

        return null;
    }

    @Override
    public void Delete(long id) {
        Session s = sf.openSession();
        Transaction tx = null;

        try {
            // Methode utilisant la généricité pour supprimer un élément d'une classe T
            T elementToUpdate = s.get(entityType, id);

            if (elementToUpdate != null) {

                tx = s.beginTransaction();
                s.remove(id);
                tx.commit();

            }
        } finally {
            s.close();
        }
    }
}

