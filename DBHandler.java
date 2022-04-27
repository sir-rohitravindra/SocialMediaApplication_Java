import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class DBHandler {

    private Statement stmt;
    private Connection c;
    private ResultSet rs;
    private UserBuilder userBuilder;
    private PostsFactory postsFactory;
    private static DBHandler singleDBHandler;

    private DBHandler() {
        userBuilder = new UserBuilder();
        postsFactory = new PostsFactory();
        System.out.println("DB Handler is up!");

    }

    static DBHandler getDBInstance() {
        if (singleDBHandler == null) {
            singleDBHandler = new DBHandler();
        }

        return singleDBHandler;
    }

    public void connectToDB() {

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/socialdb",
                    "postgres", "admin");
            // c.setAutoCommit(false);
            System.out.println("Connected to database");
        } catch (Exception e) {
            System.out.println("DBHandler.connectToDB failed \n" + e);
        }

    }

    public void tearDownConnection() {
        try {
            if (c != null) {
                c.close();
            }
            System.out.println("Connection Teardown");

        } catch (Exception e) {

            System.out.println("model.tearDownConnection failed");
        }

    }

    public void insertUserToDB(User user) {

        try {

            stmt = c.createStatement();

            String uname = user.getUsername();
            String password = user.getPassword();
            String name = user.getName();
            String bio = user.getBio();
            String gender = user.getGender();
            String query = "INSERT INTO users (uname,password,name,gender,bio) "
                    + "VALUES" + "( '" + uname + "','" + password + "','" + name + "','" + gender + "','" + bio
                    + "');";
            stmt.executeUpdate(query);
            stmt.close();
            // c.commit();
            System.out.println("Insert record Success");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("model.insertToDB() failed");
        }
    }

    public boolean VerifyLogin(User user) {

        boolean exists = false;
        try {
            // System.out.println(user);
            stmt = c.createStatement();
            String uname = user.getUsername();
            String password = user.getPassword();

            System.out.println(uname + " " + password);

            String query = "select * from users where uname = '" + uname + "' and password = '" + password
                    + "';";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                exists = true;

            }

        } catch (Exception e) {
            System.out.println("DBHandler.VerifyLogin error\n" + e);
        }

        return exists;

    }

    public boolean CheckUserExists(User user) {

        boolean exists = false;
        try {
            // System.out.println(user);
            stmt = c.createStatement();
            String uname = user.getUsername();
            // String password = user.getPassword();

            // System.out.println(uname + " " + password);

            String query = "select * from users where uname = '" + uname + "';";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                exists = true;

            }

        } catch (Exception e) {
            System.out.println("DBHandler.VerifyLogin error\n" + e);
        }

        return exists;

    }

    public User getAccount(String uname) {

        User curUser = null;

        try {

            String password = "";
            String name = "";
            String bio = "";
            String gender = "";

            stmt = c.createStatement();
            String query = "select * from users where uname = '" + uname + "';";
            rs = stmt.executeQuery(query);

            while (rs.next()) {

                password = rs.getString("password");
                name = rs.getString("name");
                gender = rs.getString("gender");
                bio = rs.getString("bio");
            }
            curUser = userBuilder.cleanSlate().setBio(bio).setGender(gender).setName(name).setPassword(password)
                    .setUsername(uname).buildUser();

        } catch (Exception e) {

            System.out.println("DBHandler.getAccount failed! " + e);
        }
        return curUser;
    }

    public void insertPostToDB(Post post, String type) {

        try {

            stmt = c.createStatement();
            String title = post.getTitle();
            String content = post.getContent();
            String postedby = post.getPostedBy();

            String query = "INSERT INTO posts (title,type,content,postedby) "
                    + "VALUES" + "( '" + title + "','" + type + "','" + content + "','" + postedby + "');";
            stmt.executeUpdate(query);
            stmt.close();
            // c.commit();
            System.out.println("Insert Post Success");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("model.insertToDB() failed");
        }
    }

    public List<Post> fetchPostsFromDB() {

        List<Post> fetched = new ArrayList<Post>();
        User curUser;
        Post fetchedPost;
        try {

            String title = "";
            String content = "";
            String type = "";
            String postedby = "";

            stmt = c.createStatement();
            String query = "select * from posts;";
            rs = stmt.executeQuery(query);

            while (rs.next()) {

                title = rs.getString("title");
                content = rs.getString("content");
                type = rs.getString("type");
                postedby = rs.getString("postedby");
                curUser = userBuilder.cleanSlate().setUsername(postedby).buildUser();
                // if (type.equals("img")) {
                // fetchedPost = new ImagePost(title, curUser);
                // fetchedPost.setPostContent(content);
                // fetchedPost.buildpost();
                // fetched.add(fetchedPost);
                // }
                fetchedPost = postsFactory.getPost(type, title, curUser, content);
                fetched.add(fetchedPost);

            }
            System.out.println("Fetch Post Success!");

        } catch (Exception e) {

            System.out.println("DBHandler.getAccount failed! " + e);
        }

        return fetched;

    }

}
