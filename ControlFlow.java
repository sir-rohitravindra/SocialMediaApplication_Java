import java.awt.event.*;

import javax.swing.JLabel;

enum Status {
    Signup,
    Login,
    MainPage
}

public class ControlFlow {
    private LoginPage loginPage;
    private SignupPage signupPage;
    private PostsPage postsPage;

    private DBHandler dbHandler;

    private ActionListenerFactory actionListenerFactory;

    // private Status cur_status;
    private User curUser;

    public ControlFlow(LoginPage loginPage, SignupPage signupPage, PostsPage postsPage, DBHandler dbHandler) {
        this.loginPage = loginPage;
        this.signupPage = signupPage;
        this.postsPage = postsPage;
        this.dbHandler = dbHandler;

        actionListenerFactory = new ActionListenerFactory();

    }

    public void StartApplication() {
        // cur_status = Status.Login;
        SetupLoginPage();
        SetupDBHandle();
        SetupSignupPage();
        LoadLoginPage();
    }

    public void LoadSignupPage() {
        loginPage.deactivatePage();
        signupPage.activatePage();
    }

    public void LoadLoginPage() {
        signupPage.deactivatePage();
        postsPage.deactivatePage();
        loginPage.activatePage();
    }

    public void LoadPostsPage() {
        loginPage.deactivatePage();
        postsPage.activatePage();
    }

    public void SetupLoginPage() {
        loginPage.AddLoginListener(actionListenerFactory.getActionListener("login_login"));
        loginPage.AddSignupListener(actionListenerFactory.getActionListener("login_signup"));
    }

    public void SetupDBHandle() {
        dbHandler.connectToDB();
    }

    public void SetupSignupPage() {
        signupPage.AddLoginListener(actionListenerFactory.getActionListener("signup_login"));
        signupPage.AddSignupListener(actionListenerFactory.getActionListener("signup_signup"));
    }

    // action Handlers
    //

    //

    //
    class HandleLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            User user = loginPage.getUserDetails();
            if (dbHandler.VerifyLogin(user)) {
                // loginPage.butJPanel.add(new JLabel("Login Successfull!"));
                // loginPage.revalidate();
                // loginPage.repaint();
                LoadPostsPage();
                curUser = dbHandler.getAccount(user.getUsername());
                postsPage.setCurUser(curUser);

            } else {
                loginPage.butJPanel.add(new JLabel("Login Failed!"));
                loginPage.revalidate();
                loginPage.repaint();
            }
        }

    }

    class CreateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User user = signupPage.getUserDetails();

            if (!dbHandler.VerifyLogin(user)) {
                dbHandler.insertToDB(user);
                LoadLoginPage();
            } else {
                signupPage.formJPanel.add(new JLabel("Signup Failed, Account Exists!"));
                signupPage.revalidate();
                signupPage.repaint();
            }
        }

    }

    class HandleBackToLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Back to login");
            LoadLoginPage();

        }

    }

    class HandleSignupListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LoadSignupPage();
        }

    }

    class ActionListenerFactory {
        ActionListener getActionListener(String Type) {
            switch (Type) {
                case "login_login":
                    return new HandleLoginListener();
                case "login_signup":
                    return new HandleSignupListener();

                case "signup_login":
                    return new HandleBackToLogin();
                case "signup_signup":
                    return new CreateAccountListener();
                default:
                    return null;
            }
        }
    }

}
