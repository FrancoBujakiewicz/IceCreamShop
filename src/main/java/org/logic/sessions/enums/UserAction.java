
 package org.logic.sessions.enums;

  public enum UserAction implements ActionToPrint

  {

    // All users have these actions without care its role
    Opt1("Change username"),
    Opt2("Change password"),
    Opt3("Log out");

    private final String action;

    UserAction(String action) { this.action = action; }

    @Override
    public String getAction() { return action; }

  }

