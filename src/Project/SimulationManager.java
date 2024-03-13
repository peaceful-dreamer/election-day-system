package Project;

import java.util.ArrayList;
import java.util.List;

public class SimulationManager implements Runnable {
    private static QueueManager entranceQueue;
    private static QueueManager voteQueue;
    private List<Voter> voters;
    private List<String> ids;
    private static List<SecurityGuard> guards;
    private static List<VotingSystem> votingSystems;
    private List<VoteTicket> voteTickets;
    private VotesCounter votesCounter;
    private static Double timeUntilClosingNumber;
    private static Integer securityGuardNumber;
    private List<Thread> servicesGiverThreads;
    private List<Thread> voterThreads;
    private List<Runnable> runnables;
    private static Boolean isOpen;
    private static Boolean finished;

    public SimulationManager(String votersDataFilePath, String idsFilePath, Integer securityGuardNumber,
            Double timeUntilClosingNumber) {
        isOpen = true;
        finished = false;

        servicesGiverThreads = new ArrayList<Thread>();
        voterThreads = new ArrayList<Thread>();
        entranceQueue = new QueueManager();
        voteQueue = new QueueManager();
        voteTickets = new ArrayList<>();
        votesCounter = new VotesCounter(voteTickets);
        guards = new ArrayList<>();
        votingSystems = new ArrayList<>();
        runnables = new ArrayList<>();

        SimulationManager.timeUntilClosingNumber = timeUntilClosingNumber;
        SimulationManager.securityGuardNumber = securityGuardNumber;

        System.out.println("Kalpi is now open!");
        System.out.println(String.format("We shall close entrance in %.1f hours from now.",
                timeUntilClosingNumber));
        System.out
                .println(String.format("You may get helpf from our %d lovely security guards.\n", securityGuardNumber));

        voters = Helper.getVotersList(votersDataFilePath, entranceQueue);
        ids = Helper.getIDList(idsFilePath);

        // create user provided number of guards
        for (Integer i = 0; i < securityGuardNumber; i++) {
            guards.add(new SecurityGuard(ids, entranceQueue, voteQueue));
        }

        // create two voting systems
        for (Integer i = 0; i < 2; i++) {
            votingSystems.add(new VotingSystem(voteTickets, voteQueue));
        }

        runnables.addAll(voters);
        runnables.addAll(guards);
        runnables.addAll(votingSystems);
    }

    public void run() {

        // start all runnables as threads and keep reference to them in a list
        for (Runnable runnable : runnables) {
            Thread nThread = new Thread(runnable);
            if (runnable instanceof Voter) {
                voterThreads.add(nThread);
            } else if (runnable instanceof ServiceGiver) {
                servicesGiverThreads.add(nThread);
            }
            nThread.start();
        }

        Thread votesCounterThread = new Thread(votesCounter);
        votesCounterThread.start();

        // wait for all voters to get in entrance queue
        for (Thread thread : voterThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // wait for all service givers to finish
        for (Thread thread : servicesGiverThreads) {
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

        if (votesCounterThread.isAlive()) {
            try {
                votesCounterThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public static Boolean getIsOpen() {
        return isOpen;
    }

    public static void setIsOpen(Boolean isOpen) {
        SimulationManager.isOpen = isOpen;
    }

    public static synchronized Boolean bothQueuesEmpty() {
        return voteQueue.isEmpty() && entranceQueue.isEmpty();
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
}
