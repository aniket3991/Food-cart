package foodcart.utility;

import java.util.Scanner;

public class Validations {

    /**
     * password validation
     *
     * @param scanner          reference of Scanner class
     * @param correctPassword  correct password of the user
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
    public static double validatePrice(Scanner scanner) {
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
                e.printStackTrace();
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
    public static int validateQuantity(Scanner scanner) {
        int quantity = 0;
        while (true) {
            try {
                System.out.println("Enter quantity: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());

                if(quantity <= 0){
                    System.out.println("Quantity should be greater then 0!!!\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Not a valid quantity\n" +
                        "Try again...\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return quantity;
    }
}
