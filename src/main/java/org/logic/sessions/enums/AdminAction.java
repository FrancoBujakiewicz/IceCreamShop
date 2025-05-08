
 package org.logic.sessions.enums;

 public enum AdminAction implements UserAction

 {
/*
   // "Inherit" from UserAction
   Opt1(CommonAction.Opt1.getAction()),
   Opt2(CommonAction.Opt2.getAction()),
   Opt3(CommonAction.Opt3.getAction()),
*/
   Opt4("Create rol") {
  @Override
  public void userAction() {
    System.out.println("pepe");
  }
};
           /*,
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
*/

   private final String action;

   AdminAction(String action) { this.action = action; }

   @Override
   public String getAction() { return action; }

 }

