package Project;

public class ServiceGiver implements Runnable {
    private Boolean isAvailable;
    protected QueueManager queue;
    protected QueueManager nextQueue;

    public ServiceGiver(QueueManager queue, QueueManager nextQueue) {
        isAvailable = true;
        this.queue = queue;
        this.nextQueue = nextQueue;
    }

    public void run() {
        while (true) {
            Voter voter = null;
            synchronized (queue) {

                while (queue.isEmpty() && !SimulationManager.isNoVoters()) {
                    // wait for new voters
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        Helper.syncPrint("InterruptedException");
                    }

                }
                this.setIsAvailable(false);
                voter = queue.getFirstVoter();

                // give service
                if (voter != null) {
                    giveService(voter, nextQueue);
                }
                this.setIsAvailable(true);

                // stop giving service after no voters are in queue and closing time passed
                if (SimulationManager.isNoVoters() && !SimulationManager.getIsOpen()) {
                    SimulationManager.notifyQueues();
                    synchronized (queue) {
                        queue.notifyAll();
                    }
                    Helper.syncPrint("%s: service giver stopped.", this.toString());

                    break;
                }
            }
        }

    }

    // for overiding
    public void giveService(Voter voter, QueueManager nextQueue) {
    }

    public synchronized void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public synchronized Boolean getIsAvailable() {
        return isAvailable;
    }
}
