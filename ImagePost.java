import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImagePost extends Post {

    BufferedImage myPicture;

    public ImagePost(String title, User user) {
        super(title, user);

    }

    @Override
    public void setPostContent(String content) {
        String correctPath = content.replace("\\", "\\\\");
        System.out.println("Corrected Path: " + correctPath);
        this.postContentString = content;
        try {
            myPicture = ImageIO.read(new File(correctPath));
            this.postContent = new JLabel(new ImageIcon(myPicture));

        } catch (Exception ex) {
            System.out.print("ImagePost.setPostContent failed ");
            System.out.println(ex);
        }
    }

}
