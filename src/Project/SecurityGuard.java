package Project;

import java.util.List;
import java.util.Random;

public class SecurityGuard {
    private List<String> voterIdList;
    private Boolean isAvailable;
    private Boolean isOpen;

    public SecurityGuard(List<String> voterIdList) {
        this.voterIdList = voterIdList;
        this.isAvailable = true;
        this.isOpen = true;
    }

    public boolean validateVoter(Voter voter) {
        this.isAvailable = false;

        Random random = new Random();
        int minSeconds = 2;
        int maxSeconds = 5;

        // Generate random duration between 2 to 5 seconds
        int randomSeconds = random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;

        // Simulate ID card verification process by waiting for the random duration
        System.out.println("Starting ID card verification...");
        try {
            // Sleep for the random duration
            Thread.sleep(randomSeconds * 1000); // Convert seconds to milliseconds
        } catch (InterruptedException e) {
            System.out.println("ID card verification process interrupted.");
        }

        if (!this.isOpen || !voterIdList.contains(voter.getIdNumber()) || voter.getAge() < 17) {
            return false;
        }
        return true;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean getIsAvailible() {
        return this.isAvailable;
    }
}