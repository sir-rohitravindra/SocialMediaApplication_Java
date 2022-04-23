import java.sql.*;

import javax.management.Query;

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
            String query = "INSERT INTO users (uname,password) "
                    + "VALUES" + "( '" + uname + "','" + password + "');";
            stmt.executeUpdate(query);
            stmt.close();
            // c.commit();
            System.out.println("Insert record Success");

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("model.insertToDB() failed");
        }
    }

    // public List<String> FetchData(String matchString) {
    // List<String> Fetched = new ArrayList<String>();
    // try {

    // stmt = c.createStatement();
    // ResultSet rs = stmt.executeQuery("SELECT * FROM movies where moviename like
    // '%" + matchString + "%';");
    // while (rs.next()) {
    // String dispString = "Movie: " + rs.getString("moviename") + " Rating: " +
    // rs.getFloat("ratings")
    // + " Date: " + rs.getString("released");
    // Fetched.add(dispString);
    // }

    // } catch (Exception e) {

    // System.out.println(e);
    // System.out.println("Model.FetchData failed");
    // }

    // return Fetched;

    // }

    public boolean VerifyLogin(User user) {

        boolean exists = false;
        try {

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
