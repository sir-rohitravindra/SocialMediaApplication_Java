import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

public class ProfilePage extends Page {

    JLabel profileImageDisplay;
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
    private String imgPath;
    private BufferedImage myPicture;

    public ProfilePage(int w, int h, String headString, Status defStatus) {
        super(w, h, headString, defStatus);

        imgPath = "C:\\Users\\rohit\\OneDrive\\Desktop\\PES Sem6\\OOAD\\InstagramClone\\UserImage.png";

        try {
            myPicture = ImageIO.read(new File(imgPath));
            profileImageDisplay = new JLabel(new ImageIcon(myPicture));
        } catch (Exception e) {
        }

        infoJPanel = new JPanel();
        infoJPanel.setLayout(new GridLayout(0, 2, 0, 10));

        unameText = new JLabel("Username");
        unameDisplay = new JLabel("Loading");

        nameText = new JLabel("Name");
        nameDisplay = new JLabel("Loading");

        genderText = new JLabel("Gender");
        genderDisplay = new JLabel("Loading");

        bioText = new JLabel("About");
        bioDisplay = new JLabel("Loading");

        logoutButton = new JButton("Logout");
        homeButton = new JButton("Back to Home");

        infoJPanel.add(profileImageDisplay);
        infoJPanel.add(nameText);
        infoJPanel.add(nameDisplay);

        infoJPanel.add(unameText);
        infoJPanel.add(unameDisplay);

        infoJPanel.add(genderText);
        infoJPanel.add(genderDisplay);

        infoJPanel.add(bioText);
        infoJPanel.add(bioDisplay);

        butJPanel = new JPanel();
        butJPanel.setLayout(new GridLayout(0, 2, 0, 20));

        butJPanel.add(homeButton);
        butJPanel.add(logoutButton);

        this.add(infoJPanel);
        this.add(butJPanel);

    }

    public void renderProfilePage(User curUser) {
        setupDisplayLabels(curUser);
        // buildInfoPanel();
        this.setLayout(new GridLayout(0, 1, 0, 10));

        this.revalidate();
        this.repaint();

    }

    public void setupDisplayLabels(User curUser) {

        System.out.println("Profile : " + curUser);
        // unameDisplay = new JLabel(curUser.getUsername());
        unameDisplay.setText(curUser.getUsername());

        // nameDisplay = new JLabel(curUser.getName());
        nameDisplay.setText(curUser.getName());

        // genderDisplay = new JLabel(curUser.getGender());
        genderDisplay.setText(curUser.getGender());

        // bioDisplay = new JLabel(curUser.getBio());
        bioDisplay.setText(curUser.getBio());

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
