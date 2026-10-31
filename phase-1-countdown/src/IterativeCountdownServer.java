//Handles one client connection at a time

import java.net.*;
import java.io.*;

public class IterativeCountdownServer {
    public static void main(String[] args) {
        int serverPort = 5000;  //default port number

        try {
            //Create listening socket locked to the given port
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Iterative Countdown Server running on port " + serverPort);

            //Server will run until stopped but handles 1 client at a time
            while (true) {
                //Wait for a connection
                Socket clientSocket = listenSocket.accept();
                System.out.println("Connected to client: " + clientSocket.getInetAddress());

                //Create input/output streams
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                //Read integer n from client
                int n = in.readInt();
                System.out.println("Client requested countdown from " + n);

                //Send countdown: n to 1
                for (int i = n; i >= 1; i--) {
                    out.writeInt(i);
                    out.flush();
                }

                System.out.println("Countdown finished for this client.");
                clientSocket.close();  //Close after serving one client
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
