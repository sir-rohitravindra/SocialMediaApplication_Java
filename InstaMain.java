import java.awt.*;

public class InstaMain {
    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double Screenwidth = screenSize.getWidth();
        double Screenheight = screenSize.getHeight();

        int loginWidth = 300;
        int loginHeight = 500;

        int signupWidth = 500;
        int signupHeight = 500;

        int postsWidth = (int) Screenwidth / 2;
        int postsHeight = (int) Screenheight - 100;

        int profileWidth = 500;
        int profileHeight = 500;

        LoginPage loginPage = new LoginPage(loginWidth, loginHeight, "Login Page", Status.Login);
        SignupPage signupPage = new SignupPage(signupWidth, signupHeight, "Signup", Status.Signup);
        PostsPage postsPage = new PostsPage(postsWidth, postsHeight, "Home", Status.MainPage);
        ProfilePage profilePage = new ProfilePage(profileWidth, profileHeight, "User Profile", Status.Profile);
        TextPostDialogPage textPostDialogPage = new TextPostDialogPage(300, 300, "New Text Post",
                Status.MainPage);
        // DBHandler dbHandler = new DBHandler();

        DBHandler dbHandler = DBHandler.getDBInstance();
        // PostsPage postsPage = new PostsPage(postsWidth, postsHeight, "Posts Page",
        // Status.MainPage);

        ControlFlow controlFlow = new ControlFlow(loginPage, signupPage, postsPage, profilePage, textPostDialogPage,
                dbHandler);

        controlFlow.StartApplication();

    }

}
