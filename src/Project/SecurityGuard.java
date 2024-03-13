package Project;

import java.util.List;
import java.util.Random;

public class SecurityGuard extends ServiceGiver {
    private List<String> voterIdList;

    public SecurityGuard(List<String> voterIdList, QueueManager entranceQueue, QueueManager voteQueue) {
        super(entranceQueue, voteQueue);
        this.voterIdList = voterIdList;
    }

    @Override
    public void giveService(Voter voter, QueueManager nextQueue) {
        if (voterValidated(voter)) {
            // pass voter
            nextQueue.voterArrived(voter);
            Helper.syncPrint("%s: %s %s passed the security check.", this.toString(), voter.getFirstName(),
                    voter.getLastName());
        } else {
            // send voter home
            Helper.syncPrint("%s: %s %s didn't pass the security check. he is going home.",
                    this.toString(), voter.getFirstName(), voter.getLastName());
        }
    }

    public Boolean voterValidated(Voter voter) {
        Random random = new Random();
        int minSeconds = 2;
        int maxSeconds = 5;

        // Generate random duration between 2 to 5 seconds
        int randomSeconds = random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;

        // Simulate ID card verification process by waiting for the random duration
        try {
            // Sleep for the random duration
            Thread.sleep(randomSeconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            Helper.syncPrint("InterruptedException");
        }

        if (voterIdList.contains(voter.getIdNumber()) && voter.getAge() >= 17) {
            return true;
        }
        return false;
    }
}