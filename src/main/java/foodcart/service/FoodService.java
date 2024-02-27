package foodcart.service;

import foodcart.entity.Food;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 */
public interface FoodService {
    /**
     * add new food details into a database
     *
     * @param food new Food details
     * @return true/false
     */
    boolean addFood(Food food);

    /**
     * update details of an existed food item
     *
     * @param food - details of the new food item
     * @return true/false
     */

    boolean updateFood(Food food, String name);

    /**
     * delete the food item form database
     *
     * @param name - name of the food
     * @return true/false
     */
    boolean deleteFood(String name);

    /**
     * find details of a single food item by its name
     *
     * @param name - name of the food
     * @return Food object
     */
    Food findFoodByName(String name);

    /**
     * details of all foods available in a database
     *
     * @return - List of all foods
     */
    List<Food> getAllFood();
}
