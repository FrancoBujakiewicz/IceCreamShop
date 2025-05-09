
 package org.logic.sessions.enums;

 public enum CommonAction implements UserAction

 {

  // All users have these actions without care its role

  Opt1("Change username")

  {

   @Override
   public void userAction() { System.out.print("d"); }

  },

  Opt2("Change password")

  {

   @Override
   public void userAction() { System.out.print("d"); }

  },

  Opt3("Log out")

  {

   @Override
   public void userAction() { System.out.print("d"); }

  };

   private final String action;

   CommonAction(String action) { this.action = action; }

   @Override
   public String getAction() { return action; }

 }

