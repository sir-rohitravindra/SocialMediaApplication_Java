import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PostsPage extends Page {

    private JMenuBar menuBar;
    private JMenu accountMenu;
    private JMenu postContentMenu;
    private User curUser;

    public PostsPage(int w, int h, String headString, Status defStatus) {
        super(w, h, headString, defStatus);
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        postContentMenu = new JMenu("Create");

        // menuBar.add(accountMenu);
        menuBar.add(postContentMenu);

    }

    public void setCurUser(User curUser) {

        this.curUser = curUser;
        accountMenu = new JMenu("User: " + curUser.getName());
        menuBar.add(accountMenu);
        menuBar.revalidate();
        // accountMenu.setText(curUser.getName());
        System.out.println(this.curUser);
        System.out.println(accountMenu.getText());
    }

}
