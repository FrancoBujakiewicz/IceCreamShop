
 package org.logic.sessions.enums;

 public enum AdminAction implements ActionToPrint

 {

   // Simulating inherit from UserAction
   Opt1(UserAction.Opt1.getAction()),
   Opt2(UserAction.Opt2.getAction()),
   Opt3(UserAction.Opt3.getAction()),

   Opt4("Create rol"),
   Opt5("Read rol"),
   Opt6("Update rol"),
   Opt7("Delete rol"),

   Opt8("Create action"),
   Opt9("Read action"),
   Opt10("Update action"),
   Opt11("Delete action"),

   Opt12("Create User"),
   Opt13("Read User"),
   Opt14("Update User"),
   Opt15("Delete User");

   private final String action;

   AdminAction(String action) { this.action = action; }

   @Override
   public String getAction() { return action; }

 }

