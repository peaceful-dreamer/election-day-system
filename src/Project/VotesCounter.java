package Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotesCounter implements Runnable {
    private List<VoteTicket> voteTickets;

    public VotesCounter(List<VoteTicket> voteTickets) {
        this.voteTickets = voteTickets;
    }

    public void run() {
        waitToCloseEntrance();
        waitUntilEverybodyFinished();
        Helper.syncPrint("Voting is over, let’s start counting");
        countTickets();
    }

    public static void waitToCloseEntrance() {
        // sleep until it is time to stop new voters from entering and handle errors if
        // needed
        try {
            Thread.sleep((long) (SimulationManager.getTimeUntilClosingNumber() * 1000)); // Convert seconds to
                                                                                         // milliseconds
        } catch (InterruptedException e) {
            Helper.syncPrint("InterruptedException");
        }

        SimulationManager.setIsOpen(false);
    }

    public void waitUntilEverybodyFinished() {
        synchronized (SimulationManager.class) {
            while (!SimulationManager.getFinished()) {
                try {
                    SimulationManager.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void countTickets() {
        List<VoteTicket> voteTickets = this.voteTickets;

        // Map to count votes for each mayor
        Map<String, Integer> mayorVotes = new HashMap<>();
        // Map to count votes for each list
        Map<String, Integer> listVotes = new HashMap<>();

        // Iterate over the voteTickets
        for (VoteTicket voteTicket : voteTickets) {
            // Count votes for each mayor
            String mayor = voteTicket.getMayorSelection();
            incrementVote(mayorVotes, mayor);

            // Count votes for each list
            String list = voteTicket.getListSelection();
            incrementVote(listVotes, list);
        }

        // Determine the next mayor
        String nextMayor = determineWinner(mayorVotes);

        // Determine the list with the most votes
        String nextParty = determineWinner(listVotes);
        // Announce the results
        Helper.syncPrint("The next mayor is: " + nextMayor);
        Helper.syncPrint("The list with most votes is: " + nextParty);
        // try {
        // Thread.currentThread().join();
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
    }

    private void incrementVote(Map<String, Integer> voteMap, String key) {
        voteMap.put(key, voteMap.getOrDefault(key, 0) + 1);
    }

    private String determineWinner(Map<String, Integer> voteMap) {
        String winner = "";
        int maxVotes = 0;
        for (Map.Entry<String, Integer> entry : voteMap.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }
        return winner;
    }

}