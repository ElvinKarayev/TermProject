package Library.GUI;

import javax.swing.*;

import Library.UserData.User;
import Library.UserData.UserDataManagement;
import Library.UserData.UserNotFound;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {

    private static JLabel userLabel;
    private static JTextField userTextField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JButton loginButton;
    private static JLabel hasNoAccountLabel;
    private static JButton registerButton;
    private static JLabel success;
    private static JPanel panel;

    public LoginGUI() {
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

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        hasNoAccountLabel = new JLabel("No account?");
        hasNoAccountLabel.setBounds(10, 150, 80, 25);
        panel.add(hasNoAccountLabel);

        registerButton = new JButton("Sign up");
        registerButton.setBounds(100, 150, 80, 25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the registration window
                new RegistrationGUI();
            }
        });

        panel.add(registerButton);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText().trim();
        char[] passwordCharacters = passwordField.getPassword();
        String password = new String(passwordCharacters).trim();

        try {
            User user = UserDataManagement.getUser(username);

            if (password.compareTo(user.getPassword()) == 0) {
                this.dispose();
                if (username.equals("admin") && password.equals("admin")) {
                    new AdminPanelGUI();
                } else {
                    new MoviesGUI(user);
                }
            } else {
                success.setText("Username or password is wrong!");
            }
        } catch (UserNotFound ex) {
            success.setText("User not found!");
        }
    }
}