import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ProfilePage extends Page {

    // username text
    JLabel unameText;
    JLabel unameDisplay;

    // name text
    JLabel nameText;
    JLabel nameDisplay;

    // gender text
    JLabel genderText;
    JLabel genderDisplay;

    // bio text
    JLabel bioText;
    JLabel bioDisplay;

    // Jpanles
    JPanel infoJPanel;
    JPanel butJPanel;

    // Navigate Buttons
    JButton logoutButton;
    JButton homeButton;

    public ProfilePage(int w, int h, String headString, Status defStatus) {
        super(w, h, headString, defStatus);

        infoJPanel = new JPanel();
        infoJPanel.setLayout(new GridLayout(0, 2, 0, 20));

        unameText = new JLabel("Username");

        nameText = new JLabel("Name");

        genderText = new JLabel("Gender");

        bioText = new JLabel("About");

        logoutButton = new JButton("Logout");
        homeButton = new JButton("Back to Home");

        butJPanel = new JPanel();
        butJPanel.setLayout(new GridLayout(0, 2, 0, 20));

        butJPanel.add(homeButton);
        butJPanel.add(logoutButton);

    }

    public void renderProfilePage(User curUser) {
        setupDisplayLabels(curUser);
        // buildInfoPanel();
        this.setLayout(new GridLayout(0, 1, 0, 10));
        this.add(infoJPanel);
        this.add(butJPanel);
        this.revalidate();
        this.repaint();

    }

    public void setupDisplayLabels(User curUser) {

        System.out.println("Profile : " + curUser);
        unameDisplay = new JLabel(curUser.getUsername());
        nameDisplay = new JLabel(curUser.getName());
        genderDisplay = new JLabel(curUser.getGender());
        bioDisplay = new JLabel(curUser.getBio());

        infoJPanel.add(nameText);
        infoJPanel.add(nameDisplay);

        infoJPanel.add(unameText);
        infoJPanel.add(unameDisplay);

        infoJPanel.add(genderText);
        infoJPanel.add(genderDisplay);

        infoJPanel.add(bioText);
        infoJPanel.add(bioDisplay);

    }

    // public void buildInfoPanel() {
    // infoJPanel.add(nameText);
    // infoJPanel.add(nameDisplay);

    // infoJPanel.add(unameText);
    // infoJPanel.add(unameDisplay);

    // infoJPanel.add(genderText);
    // infoJPanel.add(genderDisplay);

    // infoJPanel.add(bioText);
    // infoJPanel.add(bioDisplay);
    // }

    public void AddLogoutListener(ActionListener logoutActionListener) {
        logoutButton.addActionListener(logoutActionListener);
    }

    public void AddBackHomeListener(ActionListener backHomeActionListener) {
        homeButton.addActionListener(backHomeActionListener);
    }

}
