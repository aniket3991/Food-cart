package foodcart.view;

import foodcart.controller.UserController;
import foodcart.entity.User;
import foodcart.utility.Validations;

import java.util.Scanner;

/**
 * Author: Aniket Kumar Mishra
 * View for user Management
 */
public class UserManagement {
    private final UserController userController;

    public UserManagement() {
        userController = new UserController();
    }

    /**
     * Add new user into the database
     *
     * @param scanner - Scanner object
     */
    public void addUser(Scanner scanner) {
        System.out.println("Enter new user details to add:-");

        System.out.println("Enter Email Id: ");
        String email = scanner.nextLine().trim();

        User existedUser = userController.findUserByEmail(email);
        if (existedUser == null) {

            User user = takeUserDetails(scanner, email);

            if (userController.addUser(user))
                System.out.println("New user added successfully");
            else
                System.out.println("Something went wrong!!!");
        } else if (existedUser.getIsActivated() == 0) {
            System.out.println("A deactivated user! do you want to activate same account (yes/no).");
            String conformation = Validations.userConfirmation(scanner);

            if (conformation.equalsIgnoreCase("yes")) {
                if (userController.activateUser(existedUser.getId()))
                    System.out.println("User activated successfully.");
            } else
                System.out.println("You have to use different mail id for new registration");
        } else
            System.out.println("User already registered with this mail id\n");
    }

    /**
     * Update an existed user
     *
     * @param scanner - Scanner object
     */
    public void updateUser(Scanner scanner) {
        System.out.println("Enter Email Id of User: ");
        String email = scanner.nextLine().trim();

        User existedUser = userController.findUserByEmail(email);

        if (existedUser != null && existedUser.getIsActivated() != 0) {
            System.out.println("Enter updated details for the user: ");
            System.out.println("Enter email Id: ");
            String newEmail = scanner.nextLine().trim();

            User user = takeUserDetails(scanner, newEmail);

            if (userController.updateUser(user, email))
                System.out.println("User updated successfully");
            else
                System.out.println("Something went wrong!!!");
        } else
            System.out.println("User not registered.\n");

    }

    /**
     * Delete an existed user
     *
     * @param scanner - Scanner Object
     */
    public void deleteUser(Scanner scanner) {
        System.out.println("Enter user email id: ");
        String email = scanner.nextLine().trim();

        User user = userController.findUserByEmail(email);

        if (user == null || user.getIsActivated() == 0)
            System.out.println("User does not exist.\n");
        else {
            System.out.println("Do you want to delete this user(yes/no): ");
            String confirmation = Validations.userConfirmation(scanner);

            if (confirmation.equalsIgnoreCase("yes"))
                if (userController.deleteUser(user.getId()))
                    System.out.println("User removed successfully");
        }
    }

    /**
     * Taking user details from user
     *
     * @param scanner - Scanner object
     * @param email   - email of the current user
     * @return - User object after taking all details
     */
    private User takeUserDetails(Scanner scanner, String email) {
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        String role = Validations.setValidRole(scanner);

        System.out.println("Enter city: ");
        String city = scanner.nextLine().trim();

        String password = Validations.setValidPassword(scanner);

        return new User(firstName, lastName, role, email, password, city, 1);
    }
}
