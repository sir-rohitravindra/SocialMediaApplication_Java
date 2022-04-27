import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO;
import java.io.File;

public class PostsPage extends Page {

    private JMenuBar menuBar;

    private JMenu accountMenu;
    private JMenuItem profileItem;
    private JMenuItem logoutItem;

    private JMenu postContentMenu;
    private JMenu postNewItem;
    private JMenuItem newImagePostItem;
    private JMenuItem newTextPostItem;

    private JScrollPane postsJScrollPane;
    private JPanel postsJPanel;

    private User curUser;
    JFileChooser jfc;

    private List<Post> postsList;

    public PostsPage(int w, int h, String headString, Status defStatus) {
        super(w, h, headString, defStatus);

        postsList = new ArrayList<Post>();

        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

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

        newImagePostItem = new JMenuItem("New Image Post");
        newTextPostItem = new JMenuItem("New Text Post");
        postNewItem.add(newImagePostItem);
        postNewItem.add(newTextPostItem);

        menuBar.add(accountMenu);
        menuBar.add(postContentMenu);

        postsJPanel = new JPanel();
        postsJPanel.setLayout(new GridLayout(0, 1, 0, 10));
        postsJScrollPane = new JScrollPane(postsJPanel);
        this.add(postsJScrollPane);

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

    public void AddProfileListener(ActionListener profileListener) {
        profileItem.setActionCommand("Profile");
        profileItem.addActionListener(profileListener);
    }

    public void AddNewImagePostListener(ActionListener newPostListener) {
        System.out.println("Action Listener Added");
        newImagePostItem.setActionCommand("ImagePost");
        newImagePostItem.addActionListener(newPostListener);
    }

    public void AddNewTextPostListener(ActionListener newPostListener) {
        newTextPostItem.setActionCommand("TextPost");
        newTextPostItem.addActionListener(newPostListener);
    }

    public void RenderPosts(Post newPost) {
        postsList.add(newPost);
        postsJPanel.add(newPost.getPostJPanel());
        postsJPanel.revalidate();
        postsJPanel.repaint();
    }

}
