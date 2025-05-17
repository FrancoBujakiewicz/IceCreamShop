
 package org.logic;

 import com.vonage.client.VonageClient;
 import com.vonage.client.sms.MessageStatus;
 import com.vonage.client.sms.SmsSubmissionResponse;
 import com.vonage.client.sms.messages.TextMessage;

 import java.util.Scanner;

 public class Onboarding

 {

   // Use the credentials here
   static String apiKey = "";
   static String apiSecret = "";

   static VonageClient client = VonageClient.builder().apiKey(apiKey).apiSecret(apiSecret).build();

   public static Scanner userInput = new Scanner(System.in);
   static String userChoice;

   public static Long setPhoneNumber()

   {

       long phoneNumber = 0;
       boolean validNumber = false;

       do {

           System.out.println(" ");
           System.out.print("Phone number:");
           String phoneString = userInput.nextLine().trim();

           try { phoneNumber = Long.parseLong(phoneString); validNumber = true; }

           finally
           {

               if(!validNumber || phoneNumber >= 1000000000000000L) // If longer than 15 chars

               { System.out.println("Invalid phone number! Try again."); setPhoneNumber(); }

               System.out.println("Confirm the number? [Yes -> y] [No -> enter any]: ");
               userChoice = userInput.nextLine().trim();

               if((!userChoice.equalsIgnoreCase("y"))){ setPhoneNumber(); }

               System.out.println("A SMS will be sent to authenticate your number");
               sendSMS(phoneString);

               break;

           }

       }
       while(true);

       return phoneNumber;

   }

   public static void sendSMS(String phoneNumber)

   {

     String messageBody = "Hello from IceCreamShop! The action that are you trying requires authentication. Click on this link to confirm the operation:";

     TextMessage message = new TextMessage("Vonage APIs", phoneNumber, messageBody);

     SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

     if (response.getMessages().getFirst().getStatus() == MessageStatus.OK)
     { System.out.println("Message sent successfully."); }

     else { System.out.println("Message failed with error: " + response.getMessages().getFirst().getErrorText()); }

   }

 }
