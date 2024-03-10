package Archive;

public class VotesCounter {
    private VotesList votesList;

    public VotesCounter(VotesList votesList) {
        this.votesList = votesList;
    }

    public void startCounting() {
        // Start counting votes and announce the results
        Voting.out.println("Voting is over, let’s start counting");
        // ... additional logic for counting and announcing results
    }

    // Additional methods
}