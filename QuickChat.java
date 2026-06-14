/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author amant
 */
public class QuickChat {
    public static void main(String[] args) {
        Message msgTask = new Message();
        
        //Welcome Message 
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        
        boolean running = true;
        while (running) {
           String menu = "Choose an option:\n1) Send Messages\n2) Show recently sent (Coming Soon)\n3) Quit";
           String input = JOptionPane.showInputDialog(menu);
           
           if (input == null || input.equals("3")) {
               running = false;
               break;
           }
           
           if (input.equals("1")) {
               //Ask how many messages to enter
               String numStr = JOptionPane.showInputDialog("How many messages do you want to enter?");
               int numMessages = Integer.parseInt(numStr);
               
               for (int i = 0; i < numMessages; i++) {
                   //Capture Recipient
                   String cell = JOptionPane.showInputDialog("Enter Recipient Number:");
                   JOptionPane.showMessageDialog(null, msgTask.checkRecipientCell(cell));
                   
                   //Capture Message Number
                   String content = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
                   if (content.length() > 250) {
                       int over = content.length() - 250;
                       JOptionPane.showMessageDialog(null, "Message exceeds 250 characters by" + over + ". Please reduce size.");
                   }else {
                       JOptionPane.showMessageDialog(null, "Message ready to send.");
                   }
                   
                   //Generate Random 10-digit ID 
                   String randomID = "" + (long)(Math.random() * 10000000000L);
                   
                   //Final confirmation menu
                   String subMenu = "1) Send Message\n2) Disregard Message\n3 Store Message";
                   int action = Integer.parseInt(JOptionPane.showInputDialog(subMenu));
                   
                   //Show results
                   String hash = msgTask.createMessageHash(randomID, i, content);
                   String result = "ID: " + randomID + "\nHash: " + hash + "\nRecipient: " + cell + "\nStatus: " + msgTask.sendMessage(action);
                   
                   JOptionPane.showMessageDialog(null, result);
               }
           }else if (input.equals("2")) {
               JOptionPane.showMessageDialog(null, "Coming Soon.");
           }
        }
    }


}
