/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.persistence.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.logic.domain.IceCream;
import java.util.ArrayList;
import java.util.List;
import org.logic.domain.Flavor;
import org.persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author franco
 */
public class FlavorJpaController implements Serializable {

    private final EntityManagerFactory emf;

    public FlavorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Flavor flavor) {
        if (flavor.getIce_creams() == null) {
            flavor.setIce_creams(new ArrayList<IceCream>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ArrayList<IceCream> attachedIce_creams = new ArrayList<IceCream>();
            for (IceCream ice_creamsIceCreamToAttach : flavor.getIce_creams()) {
                ice_creamsIceCreamToAttach = em.getReference(ice_creamsIceCreamToAttach.getClass(), ice_creamsIceCreamToAttach.getId());
                attachedIce_creams.add(ice_creamsIceCreamToAttach);
            }
            flavor.setIce_creams(attachedIce_creams);
            em.persist(flavor);
            for (IceCream ice_creamsIceCream : flavor.getIce_creams()) {
                ice_creamsIceCream.getFlavors().add(flavor);
                ice_creamsIceCream = em.merge(ice_creamsIceCream);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Flavor flavor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Flavor persistentFlavor = em.find(Flavor.class, flavor.getId());
            ArrayList<IceCream> ice_creamsOld = persistentFlavor.getIce_creams();
            ArrayList<IceCream> ice_creamsNew = flavor.getIce_creams();
            ArrayList<IceCream> attachedIce_creamsNew = new ArrayList<IceCream>();
            for (IceCream ice_creamsNewIceCreamToAttach : ice_creamsNew) {
                ice_creamsNewIceCreamToAttach = em.getReference(ice_creamsNewIceCreamToAttach.getClass(), ice_creamsNewIceCreamToAttach.getId());
                attachedIce_creamsNew.add(ice_creamsNewIceCreamToAttach);
            }
            ice_creamsNew = attachedIce_creamsNew;
            flavor.setIce_creams(ice_creamsNew);
            flavor = em.merge(flavor);
            for (IceCream ice_creamsOldIceCream : ice_creamsOld) {
                if (!ice_creamsNew.contains(ice_creamsOldIceCream)) {
                    ice_creamsOldIceCream.getFlavors().remove(flavor);
                    ice_creamsOldIceCream = em.merge(ice_creamsOldIceCream);
                }
            }
            for (IceCream ice_creamsNewIceCream : ice_creamsNew) {
                if (!ice_creamsOld.contains(ice_creamsNewIceCream)) {
                    ice_creamsNewIceCream.getFlavors().add(flavor);
                    ice_creamsNewIceCream = em.merge(ice_creamsNewIceCream);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = flavor.getId();
                if (findFlavor(id) == null) {
                    throw new NonexistentEntityException("The flavor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Flavor flavor;
            try {
                flavor = em.getReference(Flavor.class, id);
                flavor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The flavor with id " + id + " no longer exists.", enfe);
            }
            ArrayList<IceCream> ice_creams = flavor.getIce_creams();
            for (IceCream ice_creamsIceCream : ice_creams) {
                ice_creamsIceCream.getFlavors().remove(flavor);
                ice_creamsIceCream = em.merge(ice_creamsIceCream);
            }
            em.remove(flavor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Flavor> findFlavorEntities() {
        return findFlavorEntities(true, -1, -1);
    }

    public List<Flavor> findFlavorEntities(int maxResults, int firstResult) {
        return findFlavorEntities(false, maxResults, firstResult);
    }

    private List<Flavor> findFlavorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Flavor.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Flavor findFlavor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Flavor.class, id);
        } finally {
            em.close();
        }
    }

    public int getFlavorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Flavor> rt = cq.from(Flavor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
