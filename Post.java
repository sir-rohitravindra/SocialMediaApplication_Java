import javax.swing.*;
import java.awt.*;

public abstract class Post {

    protected JPanel postPanel;
    protected JLabel postTitle;
    protected JLabel postContent;
    protected JLabel postedBy;

    public Post(String title, User user) {
        postPanel = new JPanel();
        postPanel.setLayout(new GridLayout(0, 1, 0, 10));
        postTitle = new JLabel(title);
        String postedByString = "Posted by: " + user.getUsername();
        postedBy = new JLabel(postedByString);
    }

    public JPanel getPostJPanel() {
        return this.postPanel;
    }

    public void buildpost() {
        postPanel.add(postTitle);
        postPanel.add(postContent);
        postPanel.add(postedBy);
    }

    abstract public void setPostContent(String content);
}
