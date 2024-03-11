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

    public Voter(String firstName, String lastName, String id, int age, String mayorSelection, String listSelection,
            int arrivalTime, QueueManager entranceQueue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
        this.mayorSelection = mayorSelection;
        this.listSelection = listSelection;
        this.arrivalTime = arrivalTime;
        goVote(entranceQueue);
    }

    public void goVote(QueueManager entranceQueue) {
        Thread thread = new Thread(() -> {
            goToKalpi(entranceQueue);
            // if (isEnterAllowed()) {
            // vote();
            // }
            // goHome();
        });
        thread.start();
    }

    public void goToKalpi(QueueManager entranceQueue) {
        // handle the case of another thread interrupting this thread's sleep
        try {
            Thread.sleep(getArrivalTime() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (entranceQueue) {
            entranceQueue.voterArrived(this);
        }
    }

    public Boolean passedCheckPoint() {
        return true;
    }

    public void vote() {
    }

    public void goHome() {
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getElectedMayor() {
        return mayorSelection;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getElectedParty() {
        return listSelection;
    }

    public String getIdNumber() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public Thread getThread() {
        return thread;
    }

}
