public class UserBuilder {

    private String username;
    private String password;
    private String bio;
    private String gender;
    private String name;

    public UserBuilder() {
        cleanSlate();

    }

    public UserBuilder cleanSlate() {
        username = null;
        password = null;
        bio = null;
        gender = null;
        name = null;

        return this;
    }

    public UserBuilder setUsername(String uname) {
        this.username = uname;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public UserBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public User buildUser() {
        return new User(username, password, bio, gender, name);
    }

}
