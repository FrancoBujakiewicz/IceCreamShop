package org.logic;

import org.logic.domain.Action;
import org.logic.domain.Rol;
import org.logic.domain.User;
import org.persistence.PersistenceController;

public class LogicController {

    PersistenceController PersistenceCtl = new PersistenceController();

    public void createAction(Action act){ PersistenceCtl.createAction(act); }

    public void createRol(Rol rol){ PersistenceCtl.createRol(rol); }
    
    public Rol findRol(Long id) { return PersistenceCtl.findRol(id); }

    public void editRol(Rol rol) throws Exception { PersistenceCtl.editRol(rol); }

    public void createUser(User user){ PersistenceCtl.createUser(user); }

    public User findUserByUsername(String username) { return PersistenceCtl.findUserByUsername(username); }

}
