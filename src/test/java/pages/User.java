package pages;

public class User {

    private String name;
    private String fullName;
    private String email;
    private String password;


    public User(String name, String fullName, String email, String password) {
        this.name = name;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
