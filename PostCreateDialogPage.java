import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PostCreateDialogPage extends Page {

    private JTextField titleEntry;
    protected JButton postJButton;
    private JLabel titleJLabel;

    public PostCreateDialogPage(int w, int h, String headString, Status defaultStatus) {
        super(w, h, headString, defaultStatus);
        titleEntry = new JTextField(35);

        postJButton = new JButton("Post Content");
        titleJLabel = new JLabel("Post Title");
        this.add(titleJLabel);
        this.add(titleEntry);
        this.setLayout(new GridLayout(0, 1, 0, 2));
    }

    public String getTitle() {
        return titleEntry.getText();
    }

    public void SetOnPostActionListener(ActionListener Al) {
        this.postJButton.addActionListener(Al);
    }

}
