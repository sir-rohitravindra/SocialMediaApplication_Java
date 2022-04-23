import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SignupPage extends Page {

    // username entry
    JTextField unameEntry;
    JLabel unameText;

    // password entry
    JTextField passwordEntry;
    JLabel passwdText;

    JTextField nameEntry;
    JLabel nameText;

    JTextArea bioEntry;

    // signup button
    JPanel formJPanel;
    JPanel butJPanel;

    // login button
    JButton loginButton;
    JButton signupButton;

    public SignupPage(int w, int h, String headString, Status defStatus) {
        super(w, h, headString, defStatus);

        formJPanel = new JPanel();
        formJPanel.setLayout(new GridLayout(0, 2, 10, 20));

        unameEntry = new JTextField(35);
        unameText = new JLabel("New Username");

        passwordEntry = new JTextField(35);
        passwdText = new JLabel("Set Password");

        nameEntry = new JTextField(35);
        nameText = new JLabel("Name");

        bioEntry = new JTextArea("Tell us about yourself!");
        JScrollPane bioJScrollPane = new JScrollPane(bioEntry);

        signupButton = new JButton("Signup");
        loginButton = new JButton("Back to Login?");

        formJPanel.add(unameText);
        formJPanel.add(unameEntry);

        formJPanel.add(passwdText);
        formJPanel.add(passwordEntry);

        formJPanel.add(nameText);
        formJPanel.add(nameEntry);

        formJPanel.add(bioJScrollPane);

        formJPanel.add(signupButton);
        formJPanel.add(loginButton);

        this.add(formJPanel);

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
        return passwordEntry.getText();
    }

    public String getName() {
        return nameEntry.getText();
    }

    public String getBio() {
        return bioEntry.getText();
    }

    public User getUserDetails() {
        return new User(getUsername(), getPassword());
    }

}
