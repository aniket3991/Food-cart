package foodcart.view;

import foodcart.controller.FoodController;
import foodcart.controller.UserController;
import foodcart.entity.Food;
import foodcart.entity.User;
import foodcart.utility.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Author: Aniket Kumar Mishra
 */
public class AdminView {

    private UserController userController = new UserController();
    private FoodController foodController = new FoodController();

    /**
     * Search single user details
     *
     * @param scanner - reference of Scanner class
     */
    public void searchSingleUser(Scanner scanner) {
        System.out.println("Enter the email id of the User: ");
        String email = scanner.nextLine().trim();
        User existedUser = userController.findUserByEmail(email);

        if (existedUser != null)
            System.out.println(existedUser);
        else
            System.out.println("Not a valid User\n");
    }

    /**
     * List of all Users
     */
    public void getAllUserList() {
        List<User> userList = userController.getAllUser();

        System.out.println("Details of " + userList.size() + " Users: ");
        System.out.println("----------------------------------------------------------------");

        for (User singleUser : userList)
            System.out.println(singleUser);

        System.out.println("----------------------------------------------------------------");
    }

    /**
     * add new food item in database if given food is not available
     *
     * @param scanner - reference of Scanner class
     */
    public void addFood(Scanner scanner) {

        System.out.println("Enter details of the new food item: ");

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        if (foodController.findFoodByName(name) == null) {
            double price = Validations.validatePrice(scanner);

            int quantity = Validations.validateQuantity(scanner);

            System.out.println("Enter a short description: ");
            String description = scanner.nextLine();

            Food food = new Food(name, price, quantity, description);

            if (foodController.addFood(food)) {
                System.out.println("added successfully\n");
            } else
                System.out.println("Something went wrong...");
        } else
            System.out.println("Food with the same name already available.\n");
    }

    /**
     * Update details of the existed food item
     *
     * @param scanner
     */
    public void updateFood(Scanner scanner) {
        System.out.println("Enter name of the food: ");
        String name = scanner.nextLine();

        Food food = foodController.findFoodByName(name);
        if (food != null) {
            System.out.println("Enter updated details for this food item: ");

            System.out.println("Enter name: ");
            food.setName(scanner.nextLine());

            food.setPrice(Validations.validatePrice(scanner));
            food.setQuantity(Validations.validateQuantity(scanner));

            System.out.println("Enter a short description: ");
            food.setDescription(scanner.nextLine());

            if (foodController.updateFood(food, name)) {
                System.out.println("Updated successfully\n");
            } else
                System.out.println("Something went wrong...");
        } else
            System.out.println("Food not available with the given name\n");
    }

    /**
     * delete a specific food
     *
     * @param scanner
     */
    public void deleteFood(Scanner scanner) {
        System.out.println("Enter name of the food: ");
        String name = scanner.nextLine();

        if (foodController.deleteFood(name))
            System.out.println("Deleted Successfully\n");
        else
            System.out.println("Something went wrong...\n");
    }

    /**
     * Search single food item details
     *
     * @param scanner - reference of Scanner class
     */
    public void searchSingleFood(Scanner scanner) {
        System.out.println("Enter the name of the Food: ");
        String name = scanner.nextLine().trim();

        Food existedFood = foodController.findFoodByName(name);

        if (existedFood != null)
            System.out.println(existedFood);
        else
            System.out.println("Given food is not available \n");
    }

    /**
     * get detail of all foods available in database
     */
    public void getAllFood() {
        List<Food> foodList = foodController.getAllFood();

        System.out.println("Details of " + foodList.size() + " Foods: ");
        System.out.println("----------------------------------------------------------------");

        for (Food food : foodList)
            System.out.println(food);

        System.out.println("----------------------------------------------------------------");
    }
}
