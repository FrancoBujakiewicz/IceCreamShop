
 package org.logic.sessions.enums;

  public enum UserAction

  {

    // All users have these actions without care its role
    Opt1("Change username"),
    Opt2("Change password"),
    Opt3("Log out");

    private final String action;

    UserAction(String action) { this.action = action; }

    public String getAction() { return action; }

  }

