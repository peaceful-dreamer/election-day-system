package Project;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {
    private QueueManager entranceQueue;
    private QueueManager voteQueue;
    private List<Voter> voters;
    private List<String> ids;
    private List<SecurityGuard> guards;
    private List<VotingSystem> votingSystems;
    private List<VoteTicket> voteTickets;
    private Double timeUntilClosingNumber;
    private Integer securityGuardNumber;

    public SystemManager(String votersDataFilePath, String idsFilePath, Integer securityGuardNumber,
            Double timeUntilClosingNumber) {
        entranceQueue = new QueueManager();
        voteQueue = new QueueManager();

        this.timeUntilClosingNumber = timeUntilClosingNumber;
        this.securityGuardNumber = securityGuardNumber;

        voters = Helper.getVotersList(votersDataFilePath, entranceQueue);
        ids = Helper.getIDList(idsFilePath);

        initVotingSystem();
        waitForDayFinishThread();
    }

    public void initVotingSystem() {
        voteTickets = new ArrayList<>();
        guards = new ArrayList<>();
        votingSystems = new ArrayList<>();

        // create user provided number of guards
        for (Integer i = 0; i < securityGuardNumber; i++) {
            guards.add(new SecurityGuard(ids, entranceQueue, voteQueue));
        }

        // create two voting systems
        for (Integer i = 0; i < 2; i++) {
            votingSystems.add(new VotingSystem(voteTickets, voteQueue));
        }
    }

    public void waitForDayFinishThread() {
        Thread closeDayThread = new Thread(() -> {
            // sleep until it is time to stop new voters from entering and handle errors if
            // needed
            try {
                Thread.sleep((long) (timeUntilClosingNumber * 1000)); // Convert seconds to milliseconds
            } catch (InterruptedException e) {
                Helper.syncPrint("InterruptedException");
            }

            tellEverybodyToFinish();

        });
        closeDayThread.start();
    }

    public void tellEverybodyToFinish() {
        for (SecurityGuard guard : guards) {
            guard.finishWorkDay();
        }

        Helper.syncPrint("time is over, only voters that went past security check point are allowed to finish.");

        // wait until all voters in voters queue finished.
        while (!voteQueue.isEmpty()) {
            // wait until voting queue is empty
        }

        for (VotingSystem votingSystem : votingSystems) {
            votingSystem.finishWorkDay();
        }

        Helper.syncPrint("Voting is over, let’s start counting");

        VotesCounter votesCounter = new VotesCounter(voteTickets);

    }

}
