//Client program that connects to the ElectionServer using RMI

import java.rmi.*;
import java.util.Scanner;

public class ElectionClient {
    public static void main(String[] args) {
        try {
            //Connect to the remote ElectionService
            Election election = (Election) Naming.lookup("rmi://localhost/ElectionService");

            //Variables
            Scanner sc = new Scanner(System.in);
            int choice;

            //Menu that repeats until user exits
            do {
                System.out.println("\n--- Election Menu ---");
                System.out.println("1. Vote for Alice");
                System.out.println("2. Vote for Bob");
                System.out.println("3. View Results");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    //Cast a vote for Alice
                    case 1:
                        election.castVote("Alice");
                        System.out.println("You voted for Alice!");
                        break;

                        //Cast a vote for Bob
                    case 2:
                        election.castVote("Bob");
                        System.out.println("You voted for Bob!");
                        break;

                        //Get current results
                    case 3:
                        int[] result = election.getResult();
                        System.out.println("\nCurrent Results:");
                        System.out.println("Alice: " + result[0] + " votes");
                        System.out.println("Bob: " + result[1] + " votes");
                        break;

                        //End the program
                    case 4:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 4);
            sc.close();

            //Handles connection or RMI errors
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
        }
    }
}
