package Project;

import java.util.List;
import java.util.Random;

public class SecurityGuard {
    private List<String> voterIdList;
    private Boolean isAvailable;
    private Boolean isOpen;

    public SecurityGuard(List<String> voterIdList, QueueManager entranceQueue, QueueManager voteQueue) {
        this.voterIdList = voterIdList;
        isOpen = true;
        workDay(entranceQueue, voteQueue);
    }

    public void workDay(QueueManager entranceQueue, QueueManager voteQueue) {
        Thread thread = new Thread(() -> {
            while (isOpen) {
                if (!entranceQueue.isEmpty()) {
                    Voter voter = entranceQueue.getFirstVoter();
                    if (voterValidated(voter)) {
                        // pass voter
                        voteQueue.voterArrived(voter);
                        Helper.syncPrint("voter with id %s is heading to the vote queue", voter.getIdNumber());
                    } else {
                        // send voter home
                        Helper.syncPrint("voter with id %s is not valid. he is going home.", voter.getIdNumber());
                        voter.goHome();
                    }
                }
            }
        });
        thread.start();
    }

    public Boolean voterValidated(Voter voter) {
        Random random = new Random();
        int minSeconds = 2;
        int maxSeconds = 5;

        // Generate random duration between 2 to 5 seconds
        int randomSeconds = random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;

        // Simulate ID card verification process by waiting for the random duration
        Helper.syncPrint("Starting ID card verification...");
        try {
            // Sleep for the random duration
            Thread.sleep(randomSeconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            Helper.syncPrint("ID card verification process interrupted.");
        }

        if (isOpen && voterIdList.contains(voter.getIdNumber()) && voter.getAge() >= 17) {
            return true;
        }
        return false;
    }

    public void closeCheckPost() {
        isOpen = false;
    }
}