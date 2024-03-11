package Project;

import java.util.ArrayList;
import java.util.List;

public class VotesList {
    private List<VoteTicket> voteTickets;

    public VotesList() {
        voteTickets = new ArrayList<>();
    }

    public void addVoteTicket(VoteTicket voteTicket) {
        voteTickets.add(voteTicket);
    }

    public List<VoteTicket> getVoteTickets() {
        return voteTickets;
    }
}