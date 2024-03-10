package Project;

import javax.swing.*;

import Queues.Queue;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String votersDataFilePath = "/home/etay/Documents/projects/java-course-final/src/VotersData.txt";
        String idsFilePath = "/home/etay/Documents/projects/java-course-final/src/IDLlist.txt";

        List<Voter> voters = Helper.getVotersList(votersDataFilePath);
        List<String> ids = Helper.getIDList(idsFilePath);

        Integer securityGuardNumber = 1;
        Double timeUntilClosingNumber = 8.0;

        GeneralSystem gs = new GeneralSystem(voters, ids, securityGuardNumber,
                timeUntilClosingNumber);

        // Queue<Voter> entranceVoterQueue = new Queue<>();
        // Queue<Voter> votingVoterQueue = new Queue<>();
        // private Queue<Voter> entranceVoterQueue;
        // private Queue<Voter> votingVoterQueue;

        // start the GUI
        // SwingUtilities.invokeLater(new Runnable() {
        // @Override
        // public void run() {
        // // VotingSystemGUI gui = new VotingSystemGUI();
        // // gui.setVisible(true);

        // }
        // });
    }
}
