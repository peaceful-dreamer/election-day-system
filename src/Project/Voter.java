package Project;

import java.util.ArrayList;
import java.util.List;

import Queues.Queue;

class Voter {
    private String firstName;
    private String lastName;
    private String id;
    private int age;
    private String mayorSelection;
    private String listSelection;
    private int arrivalTime;
    private Thread thread;
    private GeneralSystem generalSystem;

    public Voter(String firstName, String lastName, String id, int age, String mayorSelection, String listSelection,
            int arrivalTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
        this.mayorSelection = mayorSelection;
        this.listSelection = listSelection;
        this.arrivalTime = arrivalTime;
    }

    public void start() {
        VotingProcess process = new VotingProcess(this);
    }

    public void setGeneralSystem(GeneralSystem generalSystem) {
        this.generalSystem = generalSystem;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public String getIdNumber() {
        return this.id;
    }

    public Integer getAge() {
        return this.age;
    }

    public Thread getThread() {
        return this.thread;
    }

}
