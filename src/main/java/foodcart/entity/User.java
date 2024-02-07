package foodcart.entity;

/**
 * Author: Aniket Kumar Mishra
 * User Model
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String password;
    private String city;

    public User() {
    }

    public User(String firstName, String lastName, String role, String email, String password,
                String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public User(int id, String firstName, String lastName, String role, String email, String password,
                String city) {
        this(firstName, lastName, role, email, password, city);
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User Details :- " +
                "\nName: " + firstName + " " + lastName +
                "\nEmail:  " + email +
                "\nCity: " + city + "\n";
    }
}
