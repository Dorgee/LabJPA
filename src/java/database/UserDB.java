package database;

import models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class UserDB {

    public int insert(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            em.close();
            return 1;
        }
    }

    public int update(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(user);
            transaction.commit();

        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            em.close();
            return 1;
        }
    }

    public List<Users> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            List<Users> users = em.createNamedQuery("Users.findAll", Users.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }

    /**
     * Get a single user by their username.
     *
     * @param username The unique username.
     * @return A User object if found, null otherwise.
     * @throws NotesDBException
     */
    public Users getUser(String username) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Users users = em.find(Users.class, username);
            return users;
        } finally {
            em.close();
        }
    }

    public int delete(Users user) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.merge(user));
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            em.close();
            return 1;

        }
    }
}
