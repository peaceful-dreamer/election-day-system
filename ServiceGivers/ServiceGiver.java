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

            // wait for voters
            while (queue.isEmpty() && !SimulationManager.isNoVoters()) {
                synchronized (queue) {

                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        Helper.syncPrint("InterruptedException");
                    }
                }
            }

            // give service
            if (!queue.isEmpty()) {
                setIsAvailable(false);
                voter = queue.getFirstVoter();
                giveService(voter, nextQueue);
                this.setIsAvailable(true);
            }

            // stop giving service after no voters are in queue and closing time passed
            if (SimulationManager.isNoVoters() && !SimulationManager.getIsOpen()) {
                SimulationManager.notifyQueues();
                synchronized (queue) {
                    queue.notifyAll();
                }
                Helper.syncPrint("%s: service giver stopped.", this.toString());

                // stop service giver
                break;
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
