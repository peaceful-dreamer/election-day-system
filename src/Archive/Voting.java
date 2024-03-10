package Archive;

import Queues.Queue;
import java.util.List;

public class Voting {
    // Create a new queue for voters
    private Queue<Voter> entranceVoterQueue;
    private Queue<Voter> votingVoterQueue;
    private Boolean isOpen;
    private List<SecurityGuard> securityGuards;
    private List<Voter> voters;

    public Voting() {

    }

}
