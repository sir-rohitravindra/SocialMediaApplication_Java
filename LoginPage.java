import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginPage extends Page {

    // username entry
    JTextField unameEntry;
    JLabel unameText;

    // password entry
    JPasswordField passwordEntry;
    JLabel passwdText;

    // login button
    JButton loginButton;
    JButton signupButton;

    // signup button
    JPanel credJPanel;
    JPanel butJPanel;

    public LoginPage(int w, int h, String headString, Status defStatus) {

        super(w, h, headString, defStatus);

        credJPanel = new JPanel();
        credJPanel.setLayout(new GridLayout(0, 1, 0, 10));

        unameEntry = new JTextField(35);
        unameText = new JLabel("Username");

        passwordEntry = new JPasswordField(35);
        passwdText = new JLabel("Password");

        credJPanel.add(unameText);
        credJPanel.add(unameEntry);
        credJPanel.add(passwdText);
        credJPanel.add(passwordEntry);

        butJPanel = new JPanel();
        butJPanel.setLayout(new GridLayout(0, 2, 50, 0));

        loginButton = new JButton("Login");

        signupButton = new JButton("Signup");

        butJPanel.add(loginButton);
        butJPanel.add(signupButton);

        this.add(credJPanel);
        this.add(butJPanel);
        this.setLayout(new GridLayout(0, 1, 0, 10));
    }

    public void AddLoginListener(ActionListener loginActionListener) {
        loginButton.addActionListener(loginActionListener);
    }

    public void AddSignupListener(ActionListener signupActionListener) {
        signupButton.addActionListener(signupActionListener);
    }

    public String getUsername() {
        return unameEntry.getText();
    }

    public String getPassword() {
        return new String(passwordEntry.getPassword());
    }

    public User getUserDetails() {
        return userBuilder.cleanSlate().setUsername(getUsername()).setPassword(getPassword()).buildUser();
    }

}
