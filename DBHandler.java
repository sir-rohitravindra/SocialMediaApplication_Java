import java.sql.*;

public class DBHandler {

    private Statement stmt;
    private Connection c;
    private ResultSet rs;

    public DBHandler() {
        System.out.println("DB Handler is up!");
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

    public void insertToDB(User user) {

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
            System.out.println(user);
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
}
