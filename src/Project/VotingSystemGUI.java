package Project;

import javax.swing.*;
import javax.xml.transform.Source;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class VotingSystemGUI extends JFrame {
    private JLabel securityGuardLabel;
    private JLabel securityGuardErrorLabel;
    private JLabel closingTimeLabel;
    private JLabel closingTimeErrorLabel;
    private JLabel typeErrorLabel;
    private JTextField securityGuardTextField;
    private JTextField closingTimeTextField;
    private JButton startButton;
    private JButton exitButton;

    public VotingSystemGUI() {
        setTitle("Voting System");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        securityGuardLabel = new JLabel("Number of Security Guards (1-4):");
        securityGuardLabel.setBounds(20, 20, 200, 20);
        add(securityGuardLabel);

        securityGuardTextField = new JTextField("1");
        securityGuardTextField.setBounds(220, 20, 100, 20);
        add(securityGuardTextField);

        securityGuardErrorLabel = new JLabel("");
        securityGuardErrorLabel.setBounds(20, 40, 200, 20);
        add(securityGuardErrorLabel);

        closingTimeLabel = new JLabel("Closing Time (0-24):");
        closingTimeLabel.setBounds(20, 70, 200, 20);
        add(closingTimeLabel);

        closingTimeTextField = new JTextField("8");
        closingTimeTextField.setBounds(220, 70, 100, 20);
        add(closingTimeTextField);

        closingTimeErrorLabel = new JLabel("");
        closingTimeErrorLabel.setBounds(20, 90, 200, 20);
        add(closingTimeErrorLabel);

        typeErrorLabel = new JLabel("");
        typeErrorLabel.setBounds(20, 120, 200, 20);
        add(typeErrorLabel);

        startButton = new JButton("START");
        startButton.setBounds(80, 160, 100, 30);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppConfig.reset();
                // check if values correct
                try {
                    Integer securityGuardNumber = Integer.parseInt(securityGuardTextField.getText());
                    Double timeUntilClosingNumber = Double.parseDouble(closingTimeTextField.getText());

                    if (!AppConfig.setSecurityGuardNumber(securityGuardNumber)) {
                        securityGuardErrorLabel.setText("only 1-4 allowed");
                    }

                    if (!AppConfig.setTimeUntilClosingNumber(timeUntilClosingNumber)) {
                        closingTimeErrorLabel.setText("only 0.0-24.0 allowed");
                    }
                } catch (NumberFormatException except) {
                    typeErrorLabel.setText("only numbers allowed");
                }

                if (AppConfig.isConfigured()) {
                    AppConfig.startApp();
                }
            }
        });
        add(startButton);

        exitButton = new JButton("EXIT");
        exitButton.setBounds(220, 160, 100, 30);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the GUI window
            }
        });
        add(exitButton);

        setVisible(true);
    }

}
