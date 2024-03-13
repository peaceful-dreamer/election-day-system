package Project;

import java.util.List;
import java.util.Random;

public class VotingSystem extends ServiceGiver {
    private List<VoteTicket> voteTickets;

    public VotingSystem(List<VoteTicket> voteTickets, QueueManager queue) {
        super(queue, null);
        this.voteTickets = voteTickets;
    }

    @Override
    public void giveService(Voter voter, QueueManager nextQueue) {
        // voting process takes 2 seconds
        try {
            // Sleep for the random duration
            Thread.sleep(2000 - 100); // wait for 2 seconds minus 100ms for accepting the voter fron the queue
        } catch (InterruptedException e) {
            Helper.syncPrint("InterruptedException");
        }

        Helper.syncPrint("%s: %s %s voted.", this.toString(), voter.getFirstName(), voter.getLastName());

        VoteTicket voteTicket = createVoteTicket(voter);

        // check if vote ticket was created succesfuly
        if (voteTicket != null) {
            // vote
            voteTickets.add(voteTicket);
            Helper.syncPrint(voteTicket.toString());
        }

    }

    public VoteTicket createVoteTicket(Voter voter) {
        VoteTicket voteTicket = new VoteTicket(voter.getIdNumber(), voter.getAge(), voter.getElectedMayor(),
                voter.getElectedParty());
        // use a random number 1-5 to dtermine if vote ticket was made OK, in chance of
        // 0.2 that it didn't
        Random random = new Random();
        int minValue = 1;
        int maxValue = 5;
        int randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;

        // if vote ticket creation went fine
        if (randomNumber != 1) {
            return voteTicket;
        }
        // it didn't go fine; act aggresively!
        return null;
    }

}
