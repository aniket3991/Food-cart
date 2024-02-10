package foodcart.utility;

import java.util.Scanner;

/**
 * Author: Aniket Kumar Mishra,
 * A utility class for validation required in application
 */
public class Validations {

    /**
     * password validation
     *
     * @param scanner         reference of Scanner class
     * @param correctPassword correct password of the user
     * @return true/false
     */
    public static boolean passwordValidation(String correctPassword, Scanner scanner) {
        int attempt = 0;
        while (true) {
            System.out.println("Enter password: ");
            String password = scanner.nextLine().trim();

            if (correctPassword.equals(password))
                return true;
            else {
                if (++attempt == 3)
                    break;
                System.out.println("Wrong password! try again...");
                System.out.println(3 - attempt + " Attempt left\n");
            }
        }
        return false;
    }

    /**
     * Validate Price
     *
     * @param scanner - reference of Scanner class
     * @return price - a valid double type
     */
    public static double setValidPrice(Scanner scanner) {
        double price = 0;
        while (true) {
            try {
                System.out.println("Enter price: ");
                price = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid price\n" +
                        "Try again...\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    /**
     * Validate quantity
     *
     * @param scanner - reference of Scanner class
     * @return quantity - a valid int type
     */
    public static int setValidQuantity(Scanner scanner) {
        int quantity = 0;
        while (true) {
            try {
                System.out.println("Enter quantity: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());

                if (quantity <= 0) {
                    System.out.println("Quantity should be greater then 0!!!\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid quantity\n" +
                        "Try again...\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return quantity;
    }

    /**
     * Taking the same password form user for confirmation
     *
     * @param scanner - A Scanner Object
     * @return - valid password
     */
    public static String setValidPassword(Scanner scanner) {
        String password;

        while (true) {
            System.out.println("Enter password: ");
            password = scanner.nextLine().trim();

            System.out.println("Enter confirm password: ");
            String confirmPassword = scanner.nextLine().trim();

            if (password.equals(confirmPassword))
                break;
            else
                System.out.println("password and confirm password should be same. Try again\n");
        }
        return password;
    }

    /**
     * Validating a user role it would be either admin or user
     *
     * @param scanner - A Scanner Object
     * @return - a valid role
     */
    public static String setValidRole(Scanner scanner) {
        String role = "";

        while (true) {
            System.out.println("Enter access limit (Admin/User): ");
            role = scanner.nextLine().trim();
            if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user"))
                break;
            else {
                System.out.println("Access should be User or Admin only\n");
            }
        }
        return role;
    }

    /**
     * Taking user confirmation
     *
     * @return - user input in string
     */
    public static String userConfirmation(Scanner scanner) {
        String confirmation;

        while (true) {
            confirmation = scanner.nextLine().trim();

            if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("no"))
                break;
            System.out.println("Choose only yes/no: ");
        }
        return confirmation;
    }
}
