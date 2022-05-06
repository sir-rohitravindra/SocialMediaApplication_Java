import javax.swing.*;

public class TextPostDialogPage extends PostCreateDialogPage {
    private JTextArea postConJTextArea;
    private JLabel contentJLabel;
    private JScrollPane postContentJScrollPane;

    public TextPostDialogPage(int w, int h, String headString, Status defaultStatus) {
        super(w, h, headString, defaultStatus);
        postConJTextArea = new JTextArea();
        postContentJScrollPane = new JScrollPane(postConJTextArea);
        contentJLabel = new JLabel("Post Text");
        this.add(contentJLabel);
        this.add(postContentJScrollPane);
        this.add(postJButton);
    }

    public String getContent() {
        return this.postConJTextArea.getText();
    }

}
