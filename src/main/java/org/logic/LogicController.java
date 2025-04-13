package org.logic;

import org.logic.logic_classes.Action;
import org.logic.logic_classes.Rol;
import org.logic.logic_classes.User;
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
