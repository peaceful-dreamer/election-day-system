package Project;

import java.util.List;

import Queues.Queue;

public class GeneralSystem {
    private List<Voter> voters;
    private List<String> ids;

    private Integer securityGuardNumber;
    private Double timeUntilClosingNumber;

    public GeneralSystem(
            List<Voter> voters, List<String> ids, Integer securityGuardNumber,
            Double timeUntilClosingNumber) {
        this.voters = voters;
        this.ids = ids;
        this.securityGuardNumber = securityGuardNumber;
        this.timeUntilClosingNumber = timeUntilClosingNumber;

        start();
    }

    public void start() {
        Thread thread = new Thread(() -> {
            for (Voter voter : this.voters) {
                voter.start();
            }
        });
        thread.start();
    }
}
