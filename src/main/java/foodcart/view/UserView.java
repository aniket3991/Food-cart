package foodcart.view;

import foodcart.controller.CartController;
import foodcart.controller.FoodController;
import foodcart.entity.Cart;
import foodcart.entity.Food;
import foodcart.utility.Validations;

import java.util.List;
import java.util.Scanner;

/**
 * Author: Aniket Kumar Mishra
 * View for users
 */
public class UserView {

    private final AdminView adminView;
    private final CartController cartController;
    private final FoodController foodController;

    public UserView() {

        adminView = new AdminView();
        cartController = new CartController();
        foodController = new FoodController();
    }

    /**
     * get a list of all foods
     */
    public void getAllFood() {
        adminView.getAllFood();
    }

    /**
     * adding food to the cart for a user
     *
     * @param scanner - Reference of Scanner
     * @param cart    - a Cart object
     */
    public void addFoodToCart(Scanner scanner, Cart cart) {
        System.out.println("Enter food name: ");
        String name = scanner.nextLine();

        Food food = foodController.findFoodByName(name);
        if (food == null || food.getQuantity() == 0) {
            System.out.println("Given food item is out of stock.\n" +
                    "Please buy another item...");
        } else {
            int quantity = Validations.setValidQuantity(scanner);

            if (food.getQuantity() < quantity) {
                System.out.println("Only " + food.getQuantity() + " piece of this food item is available\n" +
                        "please chose quantity max up to " + food.getQuantity() + "\n");
            } else {
                food.setQuantity(quantity);
                if (cartController.addToCart(food, cart))
                    System.out.println("Added to the cart successfully");
                else
                    System.out.println("Try again...");
            }
        }
    }

    /**
     * Remove food from the cart
     *
     * @param scanner - scanner reference
     * @param cart    - a Cart Object to remove food item
     */
    public void removeFoodFromCart(Scanner scanner, Cart cart) {

        Food foodToRemove = null;

        System.out.println("Enter name of the food: ");
        String foodName = scanner.nextLine();

        for (Food cartFood : cart.getFoodList()) {
            if (cartFood.getName().equals(foodName)) {
                foodToRemove = cartFood;
                break;
            }
        }
        if (foodToRemove != null) {
            System.out.println(foodToRemove.getQuantity() + " quantity available in the cart\n");

            int quantityToRemove = validateQuantityToRemove(scanner, foodToRemove.getQuantity());

            if (foodToRemove.getQuantity() >= quantityToRemove) {
                foodToRemove.setQuantity(foodToRemove.getQuantity() - quantityToRemove);

                System.out.println(quantityToRemove + " " +
                        foodToRemove.getName() +
                        " item successfully removed from the cart.\n");

                if (foodToRemove.getQuantity() == 0)
                    cart.getFoodList().remove(foodToRemove);
            }
        } else
            System.out.println("Food not available in the cart.\n");
    }

    /**
     * show detail of cart items
     *
     * @param cart - a Cart object
     */
    public void seeCartDetails(Cart cart) {

        System.out.println("********************** Receipt **********************");
        System.out.printf("%-20s%10s%10s%10s%n", "Items", "Quantity", "rs./pic", "Price");
        double totalPrice = 0;
        for (Food food : cart.getFoodList()) {
            totalPrice += food.getQuantity() * food.getPrice();

            System.out.printf("%-20s%10d%10.2f%12.2f%n",
                    food.getName(),
                    food.getQuantity(),
                    food.getPrice(),
                    food.getQuantity() * food.getPrice());
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-20s%10s%10s%11.2f%n%n", "", "", "Total Price", totalPrice);
    }

    /**
     * Place user order
     *
     * @param scanner - a Scanner object
     * @param cart    - a Cart object to place user order
     */
    public void placeOrder(Scanner scanner, Cart cart) {
        if (!cart.getFoodList().isEmpty()) {
            seeCartDetails(cart);

            System.out.println("Do you want to place order (yes/no)");
            String confirmation = Validations.userConfirmation(scanner);

            if (confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Please Wait...");

                if (moveCartToOrder(cart)) {
                    cart.getFoodList().clear();
                    System.out.println("Order Confirmed! Thank you for purchasing...\n");
                } else
                    System.out.println("Something went wrong...");

            } else
                System.out.println("Order Cancelled\n");
        } else {
            System.out.println("Cart is empty.\n");
        }
    }

    /**
     * Save cart items to database
     *
     * @param cart - a Cart Object
     */
    public void saveCart(Cart cart) {
        if (cart.getFoodList() != null) {
            if (cartController.saveCart(cart))
                System.out.println("Cart Item Saved");
        }
    }

    /**
     * load items saved in cart by user
     *
     * @param userId - current user id
     * @return - List of food available in cart for this user
     */
    public List<Food> loadToCart(int userId) {
        return cartController.loadToCart(userId);
    }

    /**
     * moving item from cart table to order table
     *
     * @param cart - a Cart Object
     * @return - true/false
     */
    private boolean moveCartToOrder(Cart cart) {
        return cartController.moveCartToOrder(cart);
    }

    /**
     * validate quantity to remove from cart
     *
     * @param scanner           - scanner reference
     * @param availableQuantity - quantity available in cart
     * @return - return valid quantity given by user
     */
    private int validateQuantityToRemove(Scanner scanner, int availableQuantity) {
        int quantity;
        while (true) {
            try {
                System.out.println("Enter quantity to remove: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity < 0) {
                    System.out.println("Quantity should be 0 or greater!!!\n");
                    continue;
                }
                if (quantity > availableQuantity) {
                    System.out.println("You can remove up to " + availableQuantity + " items only\n");
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
}