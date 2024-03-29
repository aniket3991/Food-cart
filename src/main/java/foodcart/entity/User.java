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
    private int isActivated;

    public User() {
    }

    public User(String firstName, String lastName, String role, String email, String password,
                String city, int isActivated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        this.city = city;
        this.isActivated = isActivated;
    }

    public User(int id, String firstName, String lastName, String role, String email, String password,
                String city, int isActivated) {
        this(firstName, lastName, role, email, password, city, isActivated);
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
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

    public String getCity() {
        return city;
    }

    public int getIsActivated() {
        return isActivated;
    }

    @Override
    public String toString() {
        return "User Details :- " +
                "\nName: " + firstName + " " + lastName +
                "\nEmail:  " + email +
                "\nCity: " + city + "\n";
    }
}
