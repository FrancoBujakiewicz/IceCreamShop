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
import org.logic.logic_classes.Action;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.logic.logic_classes.Rol;
import org.persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author franco
 */
public class RolJpaController implements Serializable {
    
    private final EntityManagerFactory emf;
    
    public RolJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rol rol) {
        if (rol.getActions() == null) {
            rol.setActions(new HashSet<Action>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Action> attachedActions = new HashSet<Action>();
            for (Action actionsActionToAttach : rol.getActions()) {
                actionsActionToAttach = em.getReference(actionsActionToAttach.getClass(), actionsActionToAttach.getId());
                attachedActions.add(actionsActionToAttach);
            }
            rol.setActions(attachedActions);
            em.persist(rol);
            for (Action actionsAction : rol.getActions()) {
                actionsAction.getRol().add(rol);
                actionsAction = em.merge(actionsAction);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rol rol) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rol persistentRol = em.find(Rol.class, rol.getId());
            Set<Action> actionsOld = persistentRol.getActions();
            Set<Action> actionsNew = rol.getActions();
            Set<Action> attachedActionsNew = new HashSet<Action>();
            for (Action actionsNewActionToAttach : actionsNew) {
                actionsNewActionToAttach = em.getReference(actionsNewActionToAttach.getClass(), actionsNewActionToAttach.getId());
                attachedActionsNew.add(actionsNewActionToAttach);
            }
            actionsNew = attachedActionsNew;
            rol.setActions(actionsNew);
            rol = em.merge(rol);
            for (Action actionsOldAction : actionsOld) {
                if (!actionsNew.contains(actionsOldAction)) {
                    actionsOldAction.getRol().remove(rol);
                    actionsOldAction = em.merge(actionsOldAction);
                }
            }
            for (Action actionsNewAction : actionsNew) {
                if (!actionsOld.contains(actionsNewAction)) {
                    actionsNewAction.getRol().add(rol);
                    actionsNewAction = em.merge(actionsNewAction);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rol.getId();
                if (findRol(id) == null) {
                    throw new NonexistentEntityException("The rol with id " + id + " no longer exists.");
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
            Rol rol;
            try {
                rol = em.getReference(Rol.class, id);
                rol.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rol with id " + id + " no longer exists.", enfe);
            }
            Set<Action> actions = rol.getActions();
            for (Action actionsAction : actions) {
                actionsAction.getRol().remove(rol);
                actionsAction = em.merge(actionsAction);
            }
            em.remove(rol);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rol> findRolEntities() {
        return findRolEntities(true, -1, -1);
    }

    public List<Rol> findRolEntities(int maxResults, int firstResult) {
        return findRolEntities(false, maxResults, firstResult);
    }

    private List<Rol> findRolEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rol.class));
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

    public Rol findRol(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rol.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rol> rt = cq.from(Rol.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
