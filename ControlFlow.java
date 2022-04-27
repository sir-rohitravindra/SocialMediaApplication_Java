import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.util.*;
// import javax.swing.filechooser.FileNameExtensionFilter;

enum Status {
    Signup,
    Login,
    MainPage,
    Profile
}

public class ControlFlow {
    private LoginPage loginPage;
    private SignupPage signupPage;
    private PostsPage postsPage;
    private ProfilePage profilePage;

    private DBHandler dbHandler;

    private ActionListenerFactory actionListenerFactory;
    private PostsFactory postsFactory;

    // private Status cur_status;
    private User curUser;

    public ControlFlow(LoginPage loginPage, SignupPage signupPage, PostsPage postsPage, ProfilePage profilePage,
            DBHandler dbHandler) {
        this.loginPage = loginPage;
        this.signupPage = signupPage;
        this.postsPage = postsPage;
        this.profilePage = profilePage;
        this.dbHandler = dbHandler;

        actionListenerFactory = new ActionListenerFactory();
        postsFactory = new PostsFactory();

    }

    public void StartApplication() {
        // cur_status = Status.Login;
        SetupLoginPage();
        SetupDBHandle();
        SetupSignupPage();
        SetupPostsPage();
        SetupProfilePage();
        LoadLoginPage();
    }

    // routing
    public void LoadSignupPage() {
        loginPage.deactivatePage();
        signupPage.activatePage();
    }

    public void LoadLoginPage() {
        signupPage.deactivatePage();
        postsPage.deactivatePage();
        profilePage.deactivatePage();
        loginPage.activatePage();
    }

    public void LoadPostsPage() {
        loginPage.deactivatePage();
        profilePage.deactivatePage();
        postsPage.activatePage();

    }

    public void LoadProfilePage() {
        postsPage.deactivatePage();
        profilePage.activatePage();
        profilePage.renderProfilePage(curUser);
    }

    // Adding Action Listeners

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

    public void SetupPostsPage() {
        postsPage.AddLogoutListener(actionListenerFactory.getActionListener("posts_logout"));
        postsPage.AddProfileListener(actionListenerFactory.getActionListener("posts_profile"));
        postsPage.AddNewImagePostListener(actionListenerFactory.getActionListener("new_post"));
    }

    public void SetupProfilePage() {
        profilePage.AddLogoutListener(actionListenerFactory.getActionListener("profile_logout"));
        profilePage.AddBackHomeListener(actionListenerFactory.getActionListener("profile_back"));
    }

    // The Action Handlers
    //

    //

    // On login button click:(Login Page)
    // Verify if entered credentials are correct
    // Fetch stored posts from db
    // Render these posts
    class HandleLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            User user = loginPage.getUserDetails();

            if (dbHandler.VerifyLogin(user)) {
                // loginPage.butJPanel.add(new JLabel("Login Successfull!"));
                // loginPage.revalidate();
                // loginPage.repaint();
                List<Post> fetched = new ArrayList<Post>();
                LoadPostsPage();
                curUser = dbHandler.getAccount(user.getUsername());
                postsPage.setCurUser(curUser);
                fetched = dbHandler.fetchPostsFromDB();
                for (Post post : fetched) {
                    postsPage.RenderPosts(post);
                }

            } else {
                loginPage.butJPanel.add(new JLabel("Login Failed!"));
                loginPage.revalidate();
                loginPage.repaint();
            }
        }

    }

    // On create account (Signup Page)
    // Check if username exists
    // if not insert to db
    // else throw login error
    class CreateAccountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User user = signupPage.getUserDetails();

            if (!dbHandler.VerifyLogin(user)) {
                dbHandler.insertUserToDB(user);
                LoadLoginPage();
            } else {
                signupPage.formJPanel.add(new JLabel("Signup Failed, Account Exists!"));
                signupPage.revalidate();
                signupPage.repaint();
            }
        }

    }

    // On back to login button click (Signup Page)
    class HandleBackToLogin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Back to login");
            LoadLoginPage();

        }

    }

    // On signup account button click (Login page)
    class HandleSignupListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LoadSignupPage();
        }

    }

    // On view profile menu click (Posts Page menu)
    class HandleProfileView implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoadProfilePage();

        }

    }

    // On back to posts page (Profile Page)
    class HandleBackHome implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoadPostsPage();
        }

    }

    // On create new Post (Posts page menu)
    // check if type is image or text
    class HandleNewPost implements ActionListener {

        JFileChooser jfc = postsPage.jfc;

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("ImagePost")) {

                System.out.println("Generating new Image Post");
                postsPage.jfc.setAcceptAllFileFilterUsed(false);

                // set a title for the dialog
                postsPage.jfc.setDialogTitle("Select a image file");

                int r = postsPage.jfc.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {

                    String path = postsPage.jfc.getSelectedFile().getAbsolutePath();

                    try {
                        String paths[] = path.split("'\'");
                        String title = paths[paths.length - 1];
                        System.out.println(path + ":" + title);
                        // Post newPost = new ImagePost(title, curUser);
                        // newPost.setPostContent(path);
                        // newPost.buildpost();

                        Post newPost = postsFactory.getPost("img", title, curUser, path);

                        postsPage.RenderPosts(newPost);

                        dbHandler.insertPostToDB(newPost, "img");

                    } catch (Exception ex) {
                        System.out.println("ControlFlow.HandleNewPost.actionPerformed failed");
                        System.out.println(ex);
                    }

                }

            } else if (e.getActionCommand().equals("TextPost")) {
                System.out.println("Generating new Text post");
            } else {
                System.err.println("Invalid Post Type");
            }

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

                case "posts_logout":
                    return new HandleBackToLogin();

                case "posts_profile":
                    return new HandleProfileView();

                case "profile_logout":
                    return new HandleBackToLogin();

                case "profile_back":
                    return new HandleBackHome();

                case "new_post":
                    return new HandleNewPost();
                default:
                    return null;
            }
        }
    }

}
