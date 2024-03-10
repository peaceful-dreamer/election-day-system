package Project;

import Queues.Queue;

class VotingProcess {
    private Voter voter;
    // private QueueManager queueManager;
    // private SecurityCheck securityCheck;

    // public VotingProcess(QueueManager queueManager, SecurityCheck securityCheck)
    // {
    // this.queueManager = queueManager;
    // this.securityCheck = securityCheck;
    // }

    public VotingProcess(Voter voter) {
        this.voter = voter;
    }

    public void waitUntilArrival() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(this.voter.getArrivalTime() * 1000);
                // Add this voter to the entrance voter queue
                Helper.syncPrint("Voter " + this.voter.getIdNumber() + " has been added after "
                        + this.voter.getArrivalTime() + " seconds to the entrance voter queue.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    // public void initThread() {
    // this.thread = new Thread(() -> {
    // try {
    // Thread.sleep(this.voter.getArrivalTime() * 1000);
    // // Add this voter to the entrance voter queue
    // synchronized (entranceVoterQueue) {
    // entranceVoterQueue.push(this);
    // Helper.syncPrint("Voter " + name + " has been added to the entrance voter
    // queue.");
    // }
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }

    // });

    // this.thread.start();
    // }

    // public void startVotingProcess(Voter voter) {
    // // 1. Voter arrives at voting place and checks in with the queue manager
    // queueManager.addVoterToQueueAfterSecurityCheck(voter);

    // // 2. Voter waits in line until it's their turn
    // // (Some mechanism for waiting in line, maybe using synchronization
    // constructs)

    // // 3. Voter proceeds with the voting process once it's their turn
    // if (securityCheck.approveVoter(voter)) {
    // voter.vote();
    // } else {
    // voter.goHome();
    // }

    // // 4. Voter leaves the queue after completing the voting process
    // queueManager.removeVoterFromQueue(voter);
    // }
}
