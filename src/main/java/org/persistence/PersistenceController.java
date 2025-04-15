package org.persistence;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.logic.domain.Action;
import org.logic.domain.Rol;
import org.logic.domain.User;
import org.persistence.jpa_controllers.*;

public class PersistenceController {

    private static final String PERSISTENCE_UNIT = "IceCreamShopPU";
    private static EntityManagerFactory emf;

    static { emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT); }

    ActionJpaController ActionJpa = new ActionJpaController(emf);
    FlavorJpaController FlavorJpa = new FlavorJpaController(emf);
    IceCreamJpaController IceCreamJpa = new IceCreamJpaController(emf);
    InvoiceJpaController InvoiceJpa = new InvoiceJpaController(emf);
    OrdenJpaController OrdenJpa = new OrdenJpaController(emf);
    RolJpaController RolJpa = new RolJpaController(emf);
    SizeJpaController SizeJpa = new SizeJpaController(emf);
    UserJpaController UserJpa = new UserJpaController(emf);

    public void createAction(Action act) { ActionJpa.create(act); }

    public void createRol(Rol rol) { RolJpa.create(rol); }

    public Rol findRol(Long id) { return RolJpa.findRol(id); }

    public void editRol(Rol rol) throws Exception { RolJpa.edit(rol); }

    public void createUser(User user) { UserJpa.create(user); }

    public User findUserByUsername(String username) { return UserJpa.findUserByUsername(username); }



}
