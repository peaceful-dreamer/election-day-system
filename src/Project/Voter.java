package Project;

class Voter implements Runnable {
    private String firstName;
    private String lastName;
    private String id;
    private int age;
    private QueueManager entranceQueue;
    private String mayorSelection;
    private String listSelection;
    private int arrivalTime;
    private Boolean finished = false;

    public Voter(String firstName, String lastName, String id, int age, String mayorSelection, String listSelection,
            int arrivalTime, QueueManager entranceQueue) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
        this.mayorSelection = mayorSelection;
        this.listSelection = listSelection;
        this.arrivalTime = arrivalTime;
        this.entranceQueue = entranceQueue;
    }

    // voter is going to the Kalpi and then finishes running.
    public void run() {
        // wait until voter gets to Kalpi or it is cloesd
        Integer timeLeft = getArrivalTime();
        for (int i = 0; i < timeLeft && SimulationManager.getIsOpen(); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // go Kalpi is still open, enter the queue
        if (SimulationManager.getIsOpen()) {
            entranceQueue.voterArrived(this);
            Helper.syncPrint("entrance queue: %s %s got in the queue.",
                    this.getFirstName(), this.getLastName());
        }
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

    public void setFinished() {
        finished = true;
    }

    public Boolean getFinished() {
        return finished;
    }
}
