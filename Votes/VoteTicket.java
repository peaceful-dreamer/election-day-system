package Votes;

public class VoteTicket {
    private int ticketIndex;
    private String voterId;
    private int voterAge;
    private String electedMayor;
    private String partyList;

    private static int index = 0;

    public VoteTicket(String voterId, int voterAge, String electedMayor, String partyList) {
        this.ticketIndex = index;
        this.voterId = voterId;
        this.voterAge = voterAge;
        this.electedMayor = electedMayor;
        this.partyList = partyList;

        index++;
    }

    public String getMayorSelection() {
        return electedMayor;
    }

    public String getListSelection() {
        return partyList;
    }

    // for required comparison purposes
    public Integer getVoterAge() {
        return voterAge;
    }

    // for required comparison purposes
    public Integer getTicketIndex() {
        return ticketIndex;
    }

}
