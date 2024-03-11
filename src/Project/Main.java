package Project;

public class Main {
    public static void main(String[] args) {
        // enter files' full path
        AppConfig.votersDataFilePath = "/home/etay/Documents/projects/java-course-final/src/VotersData.txt";
        AppConfig.idsFilePath = "/home/etay/Documents/projects/java-course-final/src/IDList.txt";

        if (!AppConfig.isFilePathsSet()) {
            System.out.println("did you enter the correct files' full path in main function?");
        } else {
            new VotingSystemGUI();
        }
    }
}
