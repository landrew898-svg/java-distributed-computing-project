//Remote interface that gives the methods clients can call

import java.rmi.*;

public interface Election extends Remote {
    //Casting a vote for a candidate
    void castVote(String candidateName) throws RemoteException;

    //Get the total number of votes for each candidate
    int[] getResult() throws RemoteException;
}
