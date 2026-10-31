// Connects to iterative server and receives countdown

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class IterativeCountdownClient {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        try {
            //Ask for host
            System.out.print("Enter server host (default: localhost): ");
            String host = scan.nextLine();
            if (host.isEmpty()) host = "localhost";

            //Ask for port
            System.out.print("Enter server port (default: 5000): ");
            String portInput = scan.nextLine();
            int port = portInput.isEmpty() ? 5000 : Integer.parseInt(portInput);

            //Connect to server
            Socket s = new Socket(host, port);
            System.out.println("Connected to Iterative Server at " + host + ":" + port);

            //Create input/output streams
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            //Ask user for the countdown number and send to server
            System.out.print("Enter countdown start number (n): ");
            int n = scan.nextInt();
            out.writeInt(n);
            out.flush();

            //Receive and print countdown
            System.out.println("Countdown from server:");
            for (int i = n; i >= 1; i--) {
                int value = in.readInt();
                System.out.println(" -> " + value);
            }

            //Close socket connection
            s.close();
            System.out.println("Connection closed.");

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
