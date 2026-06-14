/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import java.util.Random;
import java.util.Scanner;

public class Message {

    private String string;

    Message() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String createMessageHash(String randomID, int i, String content) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String sentMessage(int action) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


//Variables to store message
    private String message;
    private String recipient;
    private String messageID;
    private int totalMessages = 0;
    private int messageNumber;
    
//Constructor
    public Message(String recipient, String message, String messageID) {
        this.message = message;
        this.messageNumber = ++totalMessages;
        this.recipient = recipient;
        this.messageID = messageID;
    }
    
    //Method to determine that message ID is less than 10 digits
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }
    
    //Method to check recipient number format
    public boolean checkRecipientCell(String cell) {
        return recipient.startsWith("+") && recipient.length() <= 13;
    }
    
    //Method to generate random 10-digit message ID
    public String generateID() {
    Random rand = new Random();
    long number = (long)(Math.random() * 1000000000L);
    return String.valueOf(number);
}

//Method to create message hash
public String createMessageHash() {
    String[] words = message.split(" ");
    
    String firstWord = words[0].toUpperCase();
    String lastWord = words[words.length - 1].toUpperCase();
    
    String firstTwo = messageID.length() >= 2 ? messageID.substring(0, 2) : "00";
    String messageNumber = null;
    
    return firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
}

//Method to handle sending logic
public String sendMessage(int choice) {
    
    if (message.length() > 250 ) {
    return "Please enter a message of less than 250 characters.";
}
    if (choice == 1) {
        return  "Message successfully sent.";
    }else if (choice ==2) {
        return "Press 0 to delete the message.";
    }else {
        return "Message successfully stored.";
    }
}

//Method to return message details
public String printMessageDeatils(String recipient, String message) {
    String messageID = null;
    return "Message ID: " + messageID +
           "\nMessage Hash: " + createMessageHash() +
           "\nRecipient: " + recipient +
           "\nMessage: " + message;            
}

//Method to return total messages sent
public int returnTotalMessages(int totalMessages) {
    return totalMessages;
}

//Getters 
public String getMessageID(String messageID) {
    return messageID;
}

public String getMessage(String message) {
    return message;
}

}

