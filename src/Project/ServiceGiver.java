package Project;

public class ServiceGiver {
    private Boolean isAvailable;
    private Boolean isOpen;
    protected QueueManager queue;

    public ServiceGiver(QueueManager queue, QueueManager nextQueue) {
        isAvailable = true;
        isOpen = true;
        this.queue = queue;
        workDay(queue, nextQueue);
    }

    public void workDay(QueueManager queue, QueueManager nextQueue) {
        Thread thread = new Thread(() -> {
            // the check getIsOpen is redundant, but good for verbosity
            while (getIsOpen()) {
                synchronized (queue) {
                    if (!queue.isEmpty()) {
                        // checking again for synchronazion
                        if (getIsOpen()) {
                            isAvailable = false;
                            Voter voter = queue.getFirstVoter();

                            // give service
                            giveService(voter, nextQueue);

                            isAvailable = true;
                        }

                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Helper.syncPrint("InterruptedException");
                }

            }
            close();
        });
        thread.start();
    }

    // for overiding
    public void giveService(Voter voter, QueueManager nextQueue) {
    }

    // for overiding
    public void close() {
    }

    public void finishWorkDay() {
        setIsOpen(false);
        while (!getIsAvailable()) {
            // wait until available
        }
    }

    public synchronized void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public synchronized Boolean getIsAvailable() {
        return isAvailable;
    }

    public synchronized void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public synchronized Boolean getIsOpen() {
        return isOpen;
    }

    public synchronized QueueManager getQueue() {
        return queue;
    }
}
