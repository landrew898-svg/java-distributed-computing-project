import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
//This class implments the Election interface and handles all the voting

import java.rmi.server.UnicastRemoteObject;

public class ElectionServer extends UnicastRemoteObject implements Election {
    public static void main(String[] args) {
        try {
            //Start RMI registry on port 1099 automatically
            LocateRegistry.createRegistry(1099);

            //Create and bind the remote object
            ElectionServer server = new ElectionServer();
            Naming.rebind("rmi://localhost/ElectionService", server);

            System.out.println("Election Server is ready...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
        }
    } //End of main

    //Vote counters for two candidates
    private int aliceVotes;
    private int bobVotes;

    //Constructor
    protected ElectionServer() throws RemoteException {
        aliceVotes = 0;
        bobVotes = 0;
    }

    //Method to cast a vote to the selected candidate
    public void castVote(String candidateName) throws RemoteException {
        if (candidateName.equalsIgnoreCase("Alice")) {
            aliceVotes++;
            System.out.println("Vote cast for Alice");
        } else if (candidateName.equalsIgnoreCase("Bob")) {
            bobVotes++;
            System.out.println("Vote cast for Bob");
        } else {
            System.out.println("Invalid candidate name: " + candidateName);
        }
    }

    //Method to get the current number of votes
    public int[] getResult() throws RemoteException {
        return new int[]{aliceVotes, bobVotes};
    }
}
