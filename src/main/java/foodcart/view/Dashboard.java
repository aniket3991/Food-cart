package foodcart.view;

import foodcart.entity.Cart;
import foodcart.utility.DBConnection;

import java.util.Scanner;

/**
 * Author: Aniket Kumar Mishra
 * Dashboard execution point of the application
 */
public class Dashboard {
    private final Scanner scanner;

    public Dashboard() {
        scanner = new Scanner(System.in);
    }

    // Entry point of the application
    public static void main(String[] args) {
        new Dashboard().menu();
    }

    /**
     * menu to show the list of features to use
     */
    public void menu() {
        LoginView loginView = new LoginView();

        int option;

        do {
            System.out.println("Welcome to Dashboard\n" +
                    "======================================\n" +
                    "Enter 1 to Admin Login\n" +
                    "Enter 2 to User Login\n" +
                    "Enter 3 to Create Account and User Management\n" +
                    "Enter 4 to exit\n");

            option = validOption();

            switch (option) {
                case 1:
                    loginView.adminLogin(scanner, "admin");
                    break;

                case 2:
                    loginView.userLogin(scanner);
                    break;

                case 3:
                    System.out.println("Only admin can use this features.\n");
                    loginView.adminLogin(scanner, "management");
                    break;

                case 4:
                    System.out.println("Exited from the Dashboard....\n" +
                            "Thanks for your visit");

                    DBConnection.closeConnection();
                    scanner.close();
                    break;

                default:
                    System.out.println("Please choose a correct option.");
                    break;
            }
        } while (option != 4);
    }

    /**
     * List of feature only for admin
     */
    public void adminDashboard() {
        AdminView adminView = new AdminView();

        int option;
        do {
            System.out.println("Enter 1 to Search Any Customer\n" +
                    "Enter 2 to Display All Customers\n" +
                    "Enter 3 to Add Food\n" +
                    "Enter 4 to Update Food\n" +
                    "Enter 5 to Delete Food\n" +
                    "Enter 6 to Search Food Details\n" +
                    "Enter 7 to Display All Food Items\n" +
                    "Enter 8 to Exit\n");

            option = validOption();

            switch (option) {
                case 1:
                    adminView.searchSingleUser(scanner);
                    break;
                case 2:
                    adminView.getAllUserList();
                    break;
                case 3:
                    adminView.addFood(scanner);
                    break;
                case 4:
                    adminView.updateFood(scanner);
                    break;
                case 5:
                    adminView.deleteFood(scanner);
                    break;
                case 6:
                    adminView.searchSingleFood(scanner);
                    break;
                case 7:
                    adminView.getAllFood();
                    break;
                case 8:
                    System.out.println("Exited from Admin section means Logged-out...\n" +
                            "************************************************************");
                    break;
                default:
                    System.out.println("Choose a correct option");
            }
        } while (option != 8);
    }

    /**
     * List of feature for user
     */
    public void userDashboard(int userId) {

        UserView userView = new UserView();
        Cart cart = new Cart(userId, userView.loadToCart(userId));

        if (!cart.getFoodList().isEmpty()) {
            System.out.println("Some Previously added items may changed in quantity according to availability");
        }

        int option;
        do {
            System.out.println(
                    "Enter 1 to Display All Food Items\n" +
                            "Enter 2 to Add Food in the Cart\n" +
                            "Enter 3 to Remove Food from the Cart\n" +
                            "Enter 4 to See Cart Details\n" +
                            "Enter 5 to Place Order\n" +
                            "Enter 6 to Exit\n");

            option = validOption();

            switch (option) {
                case 1:
                    userView.getAllFood();
                    break;
                case 2:
                    userView.addFoodToCart(scanner, cart);
                    break;
                case 3:
                    userView.removeFoodFromCart(scanner, cart);
                    break;
                case 4:
                    userView.seeCartDetails(cart);
                    break;
                case 5:
                    userView.placeOrder(scanner, cart);
                    break;
                case 6:
                    userView.saveCart(cart);
                    System.out.println("Exited from Customer section means Logged-out...\n" +
                            "************************************************************");
                    break;
                default:
                    System.out.println("Choose a correct option");
            }
        } while (option != 6);
    }

    /**
     * Feature to create and manage accounts
     */
    public void accountAndUserManagementDashboard() {
        UserManagement userManagement = new UserManagement();
        int option;
        do {
            System.out.println("Enter 1 to Create User Account\n" +
                    "Enter 2 to Update User\n" +
                    "Enter 3 to Delete User\n" +
                    "Enter 4 to exit\n");

            option = validOption();

            switch (option) {
                case 1:
                    userManagement.addUser(scanner);
                    break;
                case 2:
                    userManagement.updateUser(scanner);
                    break;
                case 3:
                    userManagement.deleteUser(scanner);
                    break;
                case 4:
                    System.out.println("Exited from User Section\n" +
                            "****************************************");
                    break;
                default:
                    System.out.println("Choose a correct option");
            }
        } while (option != 4);
    }

    /**
     * use to validate Option
     *
     * @return option - return value of option after asking form users
     */
    private int validOption() {
        int option;
        while (true) {
            try {
                option = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please choose given options only");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return option;
    }
}