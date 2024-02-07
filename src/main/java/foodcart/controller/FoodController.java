package foodcart.controller;

import foodcart.entity.Food;
import foodcart.service.FoodService;
import foodcart.service.Implementation.FoodServiceImplementation;

import java.util.List;

public class FoodController {

    private FoodService foodService = new FoodServiceImplementation();

    /**
     * add new Food item in database
     *
     * @param food new Food details
     * @return - true/false
     */
    public boolean addFood(Food food) {
        return foodService.addFood(food);
    }

    /**
     * @param food
     * @param name
     * @return
     */
    public boolean updateFood(Food food, String name) {
        return foodService.updateFood(food, name);
    }

    /**
     * delete the specific food from database
     *
     * @param name - name of the food
     * @return true/false
     */
    public boolean deleteFood(String name) {
        return foodService.deleteFood(name);
    }

    /**
     * Search details of single food item
     *
     * @param name - name of the food
     * @return - object of Food
     */
    public Food findFoodByName(String name) {
        return foodService.findFoodByName(name);
    }

    /**
     * details of all foods available in database
     * @return - List of all foods
     */
    public List<Food> getAllFood() {
        return foodService.getAllFood();
    }
}