/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.persistence.jpa_controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.logic.logic_classes.Flavor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.logic.logic_classes.IceCream;
import org.persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author franco
 */
public class IceCreamJpaController implements Serializable {

    public IceCreamJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IceCream iceCream) {
        if (iceCream.getFlavors() == null) {
            iceCream.setFlavors(new HashSet<Flavor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Flavor> attachedFlavors = new HashSet<Flavor>();
            for (Flavor flavorsFlavorToAttach : iceCream.getFlavors()) {
                flavorsFlavorToAttach = em.getReference(flavorsFlavorToAttach.getClass(), flavorsFlavorToAttach.getId());
                attachedFlavors.add(flavorsFlavorToAttach);
            }
            iceCream.setFlavors(attachedFlavors);
            em.persist(iceCream);
            for (Flavor flavorsFlavor : iceCream.getFlavors()) {
                flavorsFlavor.getIce_creams().add(iceCream);
                flavorsFlavor = em.merge(flavorsFlavor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IceCream iceCream) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IceCream persistentIceCream = em.find(IceCream.class, iceCream.getId());
            Set<Flavor> flavorsOld = persistentIceCream.getFlavors();
            Set<Flavor> flavorsNew = iceCream.getFlavors();
            Set<Flavor> attachedFlavorsNew = new HashSet<Flavor>();
            for (Flavor flavorsNewFlavorToAttach : flavorsNew) {
                flavorsNewFlavorToAttach = em.getReference(flavorsNewFlavorToAttach.getClass(), flavorsNewFlavorToAttach.getId());
                attachedFlavorsNew.add(flavorsNewFlavorToAttach);
            }
            flavorsNew = attachedFlavorsNew;
            iceCream.setFlavors(flavorsNew);
            iceCream = em.merge(iceCream);
            for (Flavor flavorsOldFlavor : flavorsOld) {
                if (!flavorsNew.contains(flavorsOldFlavor)) {
                    flavorsOldFlavor.getIce_creams().remove(iceCream);
                    flavorsOldFlavor = em.merge(flavorsOldFlavor);
                }
            }
            for (Flavor flavorsNewFlavor : flavorsNew) {
                if (!flavorsOld.contains(flavorsNewFlavor)) {
                    flavorsNewFlavor.getIce_creams().add(iceCream);
                    flavorsNewFlavor = em.merge(flavorsNewFlavor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = iceCream.getId();
                if (findIceCream(id) == null) {
                    throw new NonexistentEntityException("The iceCream with id " + id + " no longer exists.");
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
            IceCream iceCream;
            try {
                iceCream = em.getReference(IceCream.class, id);
                iceCream.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The iceCream with id " + id + " no longer exists.", enfe);
            }
            Set<Flavor> flavors = iceCream.getFlavors();
            for (Flavor flavorsFlavor : flavors) {
                flavorsFlavor.getIce_creams().remove(iceCream);
                flavorsFlavor = em.merge(flavorsFlavor);
            }
            em.remove(iceCream);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IceCream> findIceCreamEntities() {
        return findIceCreamEntities(true, -1, -1);
    }

    public List<IceCream> findIceCreamEntities(int maxResults, int firstResult) {
        return findIceCreamEntities(false, maxResults, firstResult);
    }

    private List<IceCream> findIceCreamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IceCream.class));
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

    public IceCream findIceCream(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IceCream.class, id);
        } finally {
            em.close();
        }
    }

    public int getIceCreamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IceCream> rt = cq.from(IceCream.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
