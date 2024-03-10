package Project;

import Queues.Queue;

public class QueueManager {
    private Queue<Voter> queue;

    public QueueManager() {
        queue = new Queue<>();
    }

    public void voterArrived(Voter voter) {
        queue.push(voter);
    }

    public Voter getFirstVoter() {
        return queue.pop();
    }

    public Boolean isEmpty() {
        return queue.isEmpty();
    }
}
