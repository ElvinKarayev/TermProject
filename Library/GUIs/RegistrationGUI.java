// package Library.GUIs;

import javax.swing.*;

import Library.UserData.User;
import Library.UserData.UserDataManagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationGUI extends JFrame implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userTextField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JButton registerButton;
    private static JButton hasAccountButton;
    private static JLabel success;
    private static JPanel panel;

    public RegistrationGUI() {
        panel = new JPanel();
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userTextField = new JTextField(20);
        userTextField.setBounds(100, 20, 165, 25);
        panel.add(userTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        registerButton = new JButton("Sign up");
        registerButton.setBounds(100, 80, 80, 25);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        hasAccountButton = new JButton("Has account");
        hasAccountButton.setBounds(100, 150, 120, 25);
        hasAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the login window
                new LoginGUI();
            }
        });

        panel.add(hasAccountButton);

        success = new JLabel();
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        char[] passwordCharacters = passwordField.getPassword();
        String password = new String(passwordCharacters);

        // Create a new User object
        User newUser = new User(username, password);

        // Try to add the user to the database
        if (UserDataManagement.addUser(newUser) == 1) {
            success.setText("Registered successfully!");
        } else if (UserDataManagement.addUser(newUser) == -1) {
            success.setText("Error:Couldn't add User");
        } else {
            success.setText("Error:User already exist");
        }
    }

    public static void main(String[] args) {
        RegistrationGUI registrationGUI = new RegistrationGUI();
    }
}