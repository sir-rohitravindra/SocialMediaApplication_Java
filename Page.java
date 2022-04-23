import javax.swing.*;

public abstract class Page extends JFrame {

    protected JLabel headingLabel;
    protected JPanel headingPanel;
    protected int width_, height_;
    protected Status defaultStatus;
    Status curStatus;

    public Page(int w, int h, String headString, Status defaultStatus) {
        headingLabel = new JLabel(headString);
        headingPanel = new JPanel();
        headingPanel.add(headingLabel);

        this.add(headingPanel);
        width_ = w;
        height_ = h;
        this.defaultStatus = defaultStatus;
        curStatus = defaultStatus;

        this.setTitle(headString);
    }

    public void activatePage() {
        this.setVisible(true);
        this.setSize(width_, height_);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void deactivatePage() {
        curStatus = defaultStatus;
        this.setVisible(false);
    }

    public Status getStatus() {
        return curStatus;
    }
}
