package Project;

import java.util.ArrayList;
import java.util.List;

public class SecurityCheckPost {
    private int totalCheckers;
    private List<SecurityGuard> securityGuards;

    public SecurityCheckPost(List<String> voterIdList, int totalCheckers) {
        securityGuards = new ArrayList<>();
        // Create the specified number of security guards
        for (int i = 0; i < totalCheckers; i++) {
            securityGuards.add(new SecurityGuard(voterIdList));
        }
    }

    public boolean approveVoter(Voter voter) {
        // Implement logic to distribute voters among security checkers
        // Example: For simplicity, distribute voters evenly among security guards
        for (SecurityGuard guard : securityGuards) {
            if (guard.canHandleVoter()) { // Example method in SecurityGuard class
                // Perform security check for the voter by the current guard
                boolean approved = guard.performSecurityCheck(voter); // Example method in SecurityGuard class
                if (approved) {
                    return true; // Voter approved by at least one guard
                }
            }
        }
        return false; // Voter not approved by any guard
    }
}
