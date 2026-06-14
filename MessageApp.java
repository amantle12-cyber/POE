/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;

public class MessageApp {
    static int MAX_LIMIT = 30;

    private static void displayStoredSendersAndRecipients() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    String [] sentMessages = new String[MAX_LIMIT];
    String [] disregardedMessages = new String[MAX_LIMIT];
    String [] storedMessages = new String[MAX_LIMIT];
    String [] messageIDs = new String[MAX_LIMIT];
    String [] messageHashes = new String[MAX_LIMIT];
    String [] messageFlags = new String[MAX_LIMIT];
    
    
    //Trackers
    static int sentCount = 0;
    static int disregardCount = 0;
    static int storedCount = 0;
    static int totalMessages = 0;
    
    
    //Total global count
    private static int globalCount = 0;
    
    
    public static void main(String[] args) {
        System.out.println("=== Message Tracker System v1.0 ===");
        
        //Populate system arrays
        populateData();
        
        System.out.println("\n[Menu Option 4] Stored Messages:");
        displayStoredSendersAndRecipients();
        
        System.out.println("\n[Longest Stored Message Evaluation]:");
        displayLongestStoredMessage();
        
        System.out.println("\n[Searching for Message ID: 0838884567]:");
        searchByMessageID("0838884567");
        
        System.out.println("\n[Searching for Recipient: +27838884567]:");
        searchAllByRecipient("+27838884567");
        
        System.out.println("\n[Attempting Deletionon Hash: HASH_1]:");
        deleteMessageByHash("HASH_1");
        
        System.out.println("\n=== Printing System Task Report ===");
        displayReport();
    }
    
    //Dynamic  processing method 
    public static void addMessageEntry(String id, String content, String flag) {
        if (globalCount >= MAX_LIMIT) {
            System.out.println("System Error: Maximum storage capacity reached!");
            return;
        }
        
        messageIDs[globalCount] = id;
        messageHashes[globalCount] = "HASH_" + globalCount;
        messageFlags[globalCount] = flag;

        
        if (flag.equals("Sent")){
            sentMessages[globalCount] = content;
        } else if (flag.equals("Disregard")) {
            disregardedMessages[globalCount] = content;
        } else if (flag.equals("Stored")) {
            storedMessages[globalCount] = content;
        }
        
        globalCount++;
    }
    
    public static void readJSONFile() {
        
        String jsonMockFile = "{ \"Recipient\": \"+27838884567\", \"Message\" \"Where are you? You are late! I have asked you to be on time.\", \"Flag\": \"Stored\"}";
        
       String clean = jsonMockFile.replace("{", "").replace("}", "").replace("\"", "");
       
       String[] properties =  clean.split(",");
       
       String id = properties[0].split(":")[1].trim();
       String text = properties[1].split(":")[1].trim();
       String status = properties[2].split(":")[1].trim();
       
       addMessageEntry(id, text, status);
    }
    
    public static void populateData() {
        addMessageEntry("+27834557896", "Did you get the cake?", "Sent");
        addMessageEntry("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addMessageEntry("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        addMessageEntry("0838884567", "It is dinner time !", "Sent");
        addMessageEntry("+27838884567", "Ok, I am leaving without you.", "Stored");
    }
    
    //Display all categorised as stored
    public static void displayStoredSenderAndRecipients() {
        int countFound = 0;
        
        for (int i = 0; i < globalCount; i++) {
            if (messageFlags[i] != null && messageFlags[i].equals("Stored") && messageIDs[i] != null) {
                System.out.println("Stored Record -> ID/Recipient: " + messageIDs[i] + " | Msg: " + storedMessages[i]);
                countFound++;
            }
        }
        if (countFound ==0) {
            System.out.println("No stored messages found in system memory.");
        }
    }
    
    public static String displayLongestStoredMessage() {
        String longestMsg = "";
        
        for (int i = 0; i < globalCount; i++) {
            if (messageFlags[i] != null && messageFlags[i].equals("Stored") && storedMessages[i] != null) {
                if (storedMessages[i].length() > longestMsg.length()) {
                    longestMsg = storedMessages[i];
                }
            }
        }
        
        if (!longestMsg.equals("")) {
            System.out.println("Longest Stored Message Found: \"" + longestMsg + "\"");
        } else {
            System.out.println("No valid stored messages available to measure.");
        }
        return longestMsg;
    }
    
    public static String searchByMessageID(String id) {
        for (int i = 0; i < globalCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(id)) {
                
                String foundText = "";
                if (messageFlags[i].equals("Sent")) foundText = sentMessages[i];
                if (messageFlags[i].equals("Stored")) foundText = storedMessages[i];
                if (messageFlags[i].equals("Disregard")) foundText = disregardedMessages[i];
                
                System.out.println("Match Found for ID (" + id + "): \"" + foundText + "\"");
                return foundText;
            }
        }
        System.out.println("Search Result: Message ID '" + id + "' could not be found.");
        return null;
    }
    
    public static void searchAllByRecipient(String recipient) {
        System.out.println("Scanning system logs for recipient: " + recipient);
        boolean foundAny = false;
        
        for (int i = 0; i < globalCount; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(recipient)) {
                String targetText = "";
                if (messageFlags[i].equals("Sent")) targetText = sentMessages[i];
                if (messageFlags[i].equals("Stored")) targetText = storedMessages[i];
                if (messageFlags[i].equals("Disregard")) targetText = disregardedMessages[i];
                
                System.out.println(" -> [" + messageFlags[i] + "] " + targetText);
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("No matching logs found for recipient.");
        }
    }
    
    public static boolean deleteMessageByHash(String hash) {
        for (int i = 0; i < globalCount; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(hash)) {
                
                String deletedText= "";
                if (messageFlags[i].equals("Sent")) deletedText = sentMessages[i];
                if (messageFlags[i].equals("Stored")) deletedText = storedMessages[i];
                if (messageFlags[i].equals("Disregard")) deletedText = disregardedMessages[i];
                
                System.out.println("Message: \"" + deletedText + "\" successfully deleted.");
                
                messagesIDs[i] = null;
                messageHashes[i] = null;
                messageFlags[i] = null;
                sentMessages[i] = null;
                storedMessages[i] = null;
                disregardedMessages[i] = null;
                
                return true;
            }
        }
        
        System.out.println("Deletion Failure: Target Hash code not found.");
        return false;
    }
    
    public static void displayReport() {
        System.out.println("Hash Code\tRecipient ID\t\tMessage Flag\tContent Extract");
        System.out.println("-------------------------------------------------------------------");
        
        for (int i = 0; i < globalCount; i++) {
            
            if (messageIDs[i] != null) {
                String dynamicContent = "";
                if (messageFlags[i].equals("Sent")) dynamicContent = sentMessages[i];
                if (messageFlags[i].equals("Stored")) dynamicContent = storedMessages[i];
                if (messageFlags[i].equals("Disregard")) dynamicContent = disregardedMessages[i];
                
                System.out.println(messageHashes[i] + "\t\t" + messageIDs[i] + "\t\t" messageFlags[i] + "\t\t" + dynamicContent);
            }
        }
    }
    
    public static String[] getSentMessages() { return sentMessages; }
    public static int getGlobalCount() { return globalCount; }
}
