package Project;

import Queues.Queue;

public class QueueManager {
    private Queue<Voter> queue;

    public QueueManager() {
        queue = new Queue<>();
    }

    public synchronized void voterArrived(Voter voter) {
        queue.push(voter);
    }

    public synchronized Voter getFirstVoter() {
        return queue.pop();
    }

    public synchronized Boolean isEmpty() {
        return queue.isEmpty();
    }
}
