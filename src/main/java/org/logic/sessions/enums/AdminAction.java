
 package org.logic.sessions.enums;

 public enum AdminAction implements UserAction

 {

   // "Inherit" from UserAction
   Opt1(CommonAction.Opt1),
   Opt2(CommonAction.Opt2),
   Opt3(CommonAction.Opt3),

   Opt4("Create rol")

   {

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

   private final UserAction commonAction;
   private final String action;

   AdminAction(UserAction commonAction)

   {

     this.commonAction = commonAction;
     this.action = commonAction.getAction();

   }

   AdminAction(String action)

   {

     this.commonAction = null;
     this.action = action;

   }

   @Override
   public String getAction() { return action; }

   @Override
   public void userAction()

   {

     if (commonAction != null) { commonAction.userAction(); }

     else { System.out.println("No specific action defined for " + action); }

   }

 }

