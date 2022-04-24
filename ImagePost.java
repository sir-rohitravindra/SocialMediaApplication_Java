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
        this.postContentString = correctPath;
        try {
            myPicture = ImageIO.read(new File(correctPath));
            this.postContent = new JLabel(new ImageIcon(myPicture));

        } catch (Exception ex) {
            // TODO: handle exception
            System.out.println(ex);
        }
    }

}
