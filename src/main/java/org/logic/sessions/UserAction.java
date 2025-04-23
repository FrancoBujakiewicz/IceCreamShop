
 package org.logic.sessions;

 public enum UserAction

 {

   Opt1("Change username"),
   Opt2("Change password"),
   Opt3("Log out");

   private final String value;

   UserAction(String value) { this.value = value; }

   public String getValue() { return value; }

 }
