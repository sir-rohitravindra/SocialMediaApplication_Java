import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PostsPage extends Page {

    private JMenuBar menuBar;

    private JMenu accountMenu;
    private JMenuItem profileItem;
    private JMenuItem logoutItem;

    private JMenu postContentMenu;
    private JMenu postNewItem;
    private JMenu newImagePostItem;
    private JMenu newTextPostItem;

    private User curUser;

    public PostsPage(int w, int h, String headString, Status defStatus) {
        super(w, h, headString, defStatus);
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        accountMenu = new JMenu("User: ");
        profileItem = new JMenuItem("View Profile");
        logoutItem = new JMenuItem("Logout");
        accountMenu.add(profileItem);
        accountMenu.add(logoutItem);

        postContentMenu = new JMenu("Create");
        postNewItem = new JMenu("New Post");
        postContentMenu.add(postNewItem);

        newImagePostItem = new JMenu("New Image Post");
        newTextPostItem = new JMenu("New Text Post");
        postNewItem.add(newImagePostItem);
        postNewItem.add(newTextPostItem);

        menuBar.add(accountMenu);
        menuBar.add(postContentMenu);

    }

    public void setCurUser(User curUser) {

        this.curUser = curUser;
        // accountMenu = new JMenu("User: " + curUser.getName());
        // menuBar.add(accountMenu);
        accountMenu.setText("User:" + curUser.getName());
        menuBar.revalidate();

        // System.out.println(this.curUser);
        // System.out.println(accountMenu.getText());
    }

    public void AddLogoutListener(ActionListener logoutListener) {
        logoutItem.setActionCommand("Logout");
        logoutItem.addActionListener(logoutListener);
    }

}
