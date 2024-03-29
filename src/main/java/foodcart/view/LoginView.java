package foodcart.view;

import foodcart.controller.LoginController;
import foodcart.entity.User;
import foodcart.utility.Validations;

import java.util.Scanner;

/**
 * Author: Aniket Kumar Mishra
 * View for login
 */
public class LoginView {

    private final Dashboard dashboard;
    private int userId;

    public LoginView() {
        dashboard = new Dashboard();
        userId = -1;
    }

    /**
     * admin login validation
     *
     * @param scanner - Scanner reference
     */
    public void adminLogin(Scanner scanner) {
        String output = login(scanner);

        if (output.equalsIgnoreCase("admin"))
            dashboard.adminDashboard();

        else if (output.equalsIgnoreCase("user"))
            System.out.println("You are not an Admin");
        else
            System.out.println(output);
    }

    /**
     * User login validation
     *
     * @param scanner - Scanner reference
     */
    public void userLogin(Scanner scanner) {
        String output = login(scanner);

        if (output.equalsIgnoreCase("user") || output.equalsIgnoreCase("admin")) {
            dashboard.userDashboard(userId);
        } else
            System.out.println(output);
    }

    /**
     * login method to take user data and pass it to the controller for validation
     *
     * @return String - user role
     */
    public String login(Scanner scanner) {
        LoginController loginController = new LoginController();

        System.out.println("Enter Username");
        String email = scanner.nextLine().trim();

        User user = loginController.login(email);

        if (user == null || user.getIsActivated() == 0)
            return "Not a registered user...\n";

        if (Validations.passwordValidation(user.getPassword(), scanner)) {
            this.userId = user.getId();
            return user.getRole();
        }
        return "Try again with correct credentials...";
    }
}
