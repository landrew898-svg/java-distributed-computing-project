//Handles multiple clients simultaneously using threads

import java.net.*;
import java.io.*;

public class ConcurrentCountdownServer {
    public static void main(String[] args) {
        int serverPort = 5001;  //use different port to avoid conflict

        try {
            //Create listening socket
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("Concurrent Countdown Server running on port " + serverPort);

            //Continuously accept client connections
            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Connection(clientSocket).start(); //start new thread for each client
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        clientSocket = aClientSocket;
        try {
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    //This method defines each thread's tasks
    public void run() {
        try {
            //Read the integer sent by the client
            int n = in.readInt();
            System.out.println("Thread " + this.getId() + " handling countdown from " + n);

            //Perform countdown and send back results
            for (int i = n; i >= 1; i--) {
                out.writeInt(i);
                out.flush();
            }

            System.out.println("Thread " + this.getId() + " finished countdown.");
            clientSocket.close(); //C;pse connection

        } catch (IOException e) {
            System.out.println("Thread error: " + e.getMessage());
        }
    }
}
