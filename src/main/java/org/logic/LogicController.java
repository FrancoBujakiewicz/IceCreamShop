package org.logic;

import org.logic.domain.User;
import org.persistence.PersistenceController;

public class LogicController {

    PersistenceController PersistenceCtl = new PersistenceController();

    public void createUser(User user){ PersistenceCtl.createUser(user); }

    public User findUserByUsername(String username) { return PersistenceCtl.findUserByUsername(username); }

}
