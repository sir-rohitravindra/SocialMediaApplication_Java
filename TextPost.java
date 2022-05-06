import javax.swing.JLabel;

public class TextPost extends Post {

    public TextPost(String title, User user) {
        super(title, user);
    }

    @Override
    public void setPostContent(String content) {
        this.postContent = new JLabel(content);
        this.postContentString = content;
    }

}
