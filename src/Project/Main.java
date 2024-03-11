package Project;

import javax.swing.*;

import Queues.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
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
