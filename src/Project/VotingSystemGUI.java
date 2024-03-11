package Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VotingSystemGUI implements ActionListener {
    private JLabel securityGuardLabel;
    private JLabel securityGuardErrorLabel;
    private JLabel closingTimeLabel;
    private JLabel closingTimeErrorLabel;
    private JLabel typeErrorLabel;
    private JTextField securityGuardTextField;
    private JTextField closingTimeTextField;
    private JButton start;
    private JButton close;
    private JPanel panel;
    private int windowWidth;
    private int WindowHight;
    private JFrame frame;

    public VotingSystemGUI() {
        windowWidth = 370;
        WindowHight = 250;

        panel = new JPanel();
        panel.setLayout(null);

        securityGuardLabel = new JLabel("Number of Security Guards (1-4):");
        securityGuardLabel.setBounds(20, 20, 200, 20);
        panel.add(securityGuardLabel);

        securityGuardTextField = new JTextField("1");
        securityGuardTextField.setBounds(220, 20, 100, 20);
        panel.add(securityGuardTextField);

        securityGuardErrorLabel = new JLabel("");
        securityGuardErrorLabel.setBounds(20, 40, 200, 20);
        panel.add(securityGuardErrorLabel);

        closingTimeLabel = new JLabel("Closing Time (0-24):");
        closingTimeLabel.setBounds(20, 70, 200, 20);
        panel.add(closingTimeLabel);

        closingTimeTextField = new JTextField("8");
        closingTimeTextField.setBounds(220, 70, 100, 20);
        panel.add(closingTimeTextField);

        closingTimeErrorLabel = new JLabel("");
        closingTimeErrorLabel.setBounds(20, 90, 200, 20);
        panel.add(closingTimeErrorLabel);

        typeErrorLabel = new JLabel("");
        typeErrorLabel.setBounds(20, 120, 200, 20);
        panel.add(typeErrorLabel);

        start = new JButton("Start");
        close = new JButton("Exit");
        start.addActionListener(this);
        start.setActionCommand("Start");
        close.addActionListener(this);
        close.setActionCommand("Close");
        start.setBounds(20, 160, 100, 30);
        close.setBounds(220, 160, 100, 30);

        start.addActionListener(this);
        start.setActionCommand("Start");
        close.addActionListener(this);
        close.setActionCommand("Close");

        panel.add(start);
        panel.add(close);

        frame = new JFrame("Kalpi simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setContentPane(panel);
        frame.setPreferredSize(new Dimension(windowWidth, WindowHight));
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if ("Start".equals(arg0.getActionCommand())) {
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
        } else if ("Close".equals(arg0.getActionCommand())) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
