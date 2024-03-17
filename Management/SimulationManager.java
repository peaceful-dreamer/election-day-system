package Management;

import java.util.ArrayList;
import java.util.List;

import Helper.Helper;
import Queue.QueueManager;
import ServiceGivers.SecurityGuard;
import ServiceGivers.ServiceGiver;
import ServiceGivers.VotingSystem;
import Voter.Voter;
import Votes.VoteTicket;
import Votes.VotesCounter;

public class SimulationManager implements Runnable {
    // static attributes
    private static QueueManager entranceQueue;
    private static QueueManager voteQueue;
    private static List<SecurityGuard> guards;
    private static List<VotingSystem> votingSystems;
    private static Double timeUntilClosingNumber;
    private static Integer securityGuardNumber;
    private static Boolean isOpen;
    private static Boolean finished;

    // object attributes
    private List<Voter> voters;
    private List<String> ids;
    private List<VoteTicket> voteTickets;
    private VotesCounter votesCounter;
    private List<Thread> threads;
    private List<Runnable> runnables;

    public SimulationManager(String votersDataFilePath, String idsFilePath, Integer securityGuardNumber,
            Double timeUntilClosingNumber) {
        // initialize static attributes
        SimulationManager.isOpen = true;
        SimulationManager.finished = false;
        SimulationManager.timeUntilClosingNumber = timeUntilClosingNumber;
        SimulationManager.securityGuardNumber = securityGuardNumber;
        SimulationManager.guards = new ArrayList<>();
        SimulationManager.votingSystems = new ArrayList<>();
        SimulationManager.entranceQueue = new QueueManager();
        SimulationManager.voteQueue = new QueueManager();

        // initialize object attributes
        voteTickets = new ArrayList<>();
        runnables = new ArrayList<>();
        votesCounter = new VotesCounter(voteTickets);
        threads = new ArrayList<Thread>();
        voters = Helper.getVotersList(votersDataFilePath);
        ids = Helper.getIDList(idsFilePath);

        // print start message
        System.out.println("\nThe voting palce is now open!");
        System.out.println(String.format("We shall close entrance in %.1f hours from now.",
                timeUntilClosingNumber));
        System.out.println(String.format("the place is secured by %d of our lovely security.\n", securityGuardNumber));

        // create user provided number of guards
        for (Integer i = 0; i < securityGuardNumber; i++) {
            guards.add(new SecurityGuard(ids, entranceQueue, voteQueue));
        }

        // create two voting systems
        for (Integer i = 0; i < 2; i++) {
            votingSystems.add(new VotingSystem(voteTickets, voteQueue));
        }

        // add all runnables to a list
        runnables.addAll(voters);
        runnables.addAll(guards);
        runnables.addAll(votingSystems);
    }

    public void run() {
        // start all runnables
        for (Runnable runnable : runnables) {
            Thread nThread = new Thread(runnable);
            threads.add(nThread);
            nThread.start();
        }

        Thread votesCounterThread = new Thread(votesCounter);
        votesCounterThread.start();

        // wait for all runnables to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // tell the votes counter thread that everybody finished
        synchronized (SimulationManager.class) {
            SimulationManager.setFinished();
            SimulationManager.class.notifyAll();
        }

        try {
            votesCounterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void notifyQueues() {
        synchronized (entranceQueue) {
            entranceQueue.notifyAll();
        }
        synchronized (voteQueue) {
            voteQueue.notifyAll();
        }
    }

    public static synchronized Boolean bothQueuesEmpty() {
        return voteQueue.isEmpty() && entranceQueue.isEmpty();
    }

    public static Boolean isAllServiceGiversAvailible() {
        List<ServiceGiver> serviceGivers = new ArrayList<>();
        serviceGivers.addAll(guards);
        serviceGivers.addAll(votingSystems);

        for (ServiceGiver serviceGiver : serviceGivers) {
            if (!serviceGiver.getIsAvailable()) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isNoVoters() {
        return bothQueuesEmpty() && isAllServiceGiversAvailible();
    }

    // getters and setters
    public static Boolean getIsOpen() {
        return isOpen;
    }

    public static void setIsOpen(Boolean isOpen) {
        SimulationManager.isOpen = isOpen;
    }

    public static Double getTimeUntilClosingNumber() {
        return SimulationManager.timeUntilClosingNumber;
    }

    public static synchronized Boolean getFinished() {
        return finished;
    }

    public static synchronized void setFinished() {
        finished = true;
    }

    public static QueueManager getEntranceQueue() {
        return entranceQueue;
    }

    public static QueueManager getVoteQueue() {
        return voteQueue;
    }

}
