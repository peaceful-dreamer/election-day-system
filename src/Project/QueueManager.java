package Project;

import Queues.Queue;

public class QueueManager {
    private Queue<Voter> queue;
    private Boolean finished;

    public QueueManager() {
        queue = new Queue<>();
    }

    public synchronized void voterArrived(Voter voter) {
        synchronized (this) {
            queue.push(voter);
            this.notifyAll();
        }
    }

    public Voter getFirstVoter() {
        Voter voter;
        synchronized (this) {
            voter = queue.pop();
            this.notifyAll();
        }
        return voter;
    }

    public Boolean isEmpty() {
        return queue.isEmpty();
    }

    public Integer getSize() {
        return queue.getSize();
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Boolean getFinished() {
        return finished;
    }

}
