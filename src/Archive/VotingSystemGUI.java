package Archive;

import javax.swing.*;
import javax.xml.transform.Source;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotingSystemGUI extends JFrame {
    private JLabel securityGuardLabel;
    private JLabel closingTimeLabel;
    private JTextField securityGuardTextField;
    private JTextField closingTimeTextField;
    private JButton startButton;
    private JButton exitButton;

    public VotingSystemGUI() {
        setTitle("Voting System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        securityGuardLabel = new JLabel("Number of Security Guards (1-4):");
        securityGuardLabel.setBounds(20, 20, 200, 20);
        add(securityGuardLabel);

        securityGuardTextField = new JTextField("1");
        securityGuardTextField.setBounds(220, 20, 100, 20);
        add(securityGuardTextField);

        closingTimeLabel = new JLabel("Closing Time (0-24):");
        closingTimeLabel.setBounds(20, 50, 200, 20);
        add(closingTimeLabel);

        closingTimeTextField = new JTextField("8");
        closingTimeTextField.setBounds(220, 50, 100, 20);
        add(closingTimeTextField);

        startButton = new JButton("START");
        startButton.setBounds(80, 100, 100, 30);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer securityGuardNumber = Integer.parseInt(securityGuardTextField.getText());
                    Float closingTimeNumber = Float.parseFloat(closingTimeTextField.getText());

                    Voting.out.println(securityGuardNumber);
                    Voting.out.println(closingTimeNumber);

                    // String temp = securityGuardTextField.getText();

                    // Simulation.tech_crews_time(Integer.parseInt(temp));
                    // temp = SecurityTime.getText();
                    // Simulation.set_security_time(Double.parseDouble(temp));
                    // QueueFactory.new_day();
                    // Source.main(null);
                } catch (Exception except) {
                    Voting.out.println("Bad input, please insert a number in both fields");
                }

            }
        });
        add(startButton);

        exitButton = new JButton("EXIT");
        exitButton.setBounds(220, 100, 100, 30);
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
