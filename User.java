public class User {

    private String username;
    private String password;
    private String bio;
    private String gender;
    private String name;

    public User(String uname, String pswd, String bio, String gender, String name) {
        this.username = uname;
        this.password = pswd;
        this.bio = bio;
        this.gender = gender;
        this.name = name;
    }

    public String getUsername() {
        return username;

    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;

    }

    public String getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public String toString() {
        return ("u:" + username + ", p:" + password + ", n:" + name + ", b:" + bio + ",g :" + gender);
    }

}
