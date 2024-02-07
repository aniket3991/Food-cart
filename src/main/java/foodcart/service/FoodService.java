package foodcart.service;

import foodcart.entity.Food;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 */
public interface FoodService {
    /**
     * add new food details into database
     * @param food new Food details
     * @return true/false
     */
    public boolean addFood(Food food);

    /**
     * update details of an existed food item
     * @param food - details of the new food item
     * @return true/false
     */

    public boolean updateFood(Food food, String name);

    /**
     * delete the food item form database
     * @param name - name of the food
     * @return true/false
     */
    public boolean deleteFood(String name);

    /**
     * find details of single food item by its name
     * @param name - name of the food
     * @return Food object
     */
    public Food findFoodByName(String name);

    /**
     * details of all foods available in database
     * @return - List of all foods
     */
    public List<Food> getAllFood();
}
