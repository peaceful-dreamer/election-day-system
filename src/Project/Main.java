package Project;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // enter files' full path
        AppConfig.votersDataFilePath = "";
        AppConfig.idsFilePath = "";

        if (!AppConfig.isFilePathsSet()) {
            System.out.println("did you enter the correct files' full path in main function?");
        } else {
            // start the GUI
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    VotingSystemGUI gui = new VotingSystemGUI();
                    gui.setVisible(true);
                }
            });
        }
    }
}
