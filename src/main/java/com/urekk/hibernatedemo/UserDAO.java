package com.urekk.hibernatedemo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAO {

    // find all
    public List<User> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
    // find one by email
    public User findOneByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<User> query = em.createQuery("select u from User u where email = :email", User.class);
        query.setParameter("email", email);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return user;
    }

    // update
    public void update(User u) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    // delete
    public void delete(User u) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.remove(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    // create new
    public void save(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
