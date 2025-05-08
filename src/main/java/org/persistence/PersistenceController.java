package org.persistence;

import org.persistence.controllers.UserJpaController;
import org.persistence.controllers.IceCreamJpaController;
import org.persistence.controllers.OrdenJpaController;
import org.persistence.controllers.InvoiceJpaController;
import org.persistence.controllers.SizeJpaController;
import org.persistence.controllers.FlavorJpaController;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.logic.domain.User;

public class PersistenceController {

    private static final String PERSISTENCE_UNIT = "IceCreamShopPU";
    private static EntityManagerFactory emf;

    static { emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT); }

    FlavorJpaController FlavorJpa = new FlavorJpaController(emf);
    IceCreamJpaController IceCreamJpa = new IceCreamJpaController(emf);
    InvoiceJpaController InvoiceJpa = new InvoiceJpaController(emf);
    OrdenJpaController OrdenJpa = new OrdenJpaController(emf);
    SizeJpaController SizeJpa = new SizeJpaController(emf);
    UserJpaController UserJpa = new UserJpaController(emf);

    public void createUser(User user) { UserJpa.create(user); }

    public User findUserByUsername(String username) { return UserJpa.findUserByUsername(username); }

}
