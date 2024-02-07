package foodcart.dao;

import foodcart.entity.Food;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 */
public interface FoodDao {
    /**
     *
     * @param food
     * @return
     */
    public boolean addFood(Food food);

    /**
     *
     * @param food
     * @param name
     * @return
     */
    public boolean updateFood(Food food, String name);

    public boolean deleteFood(String name);

    /**
     * detail of single food item
     * @param name - name of food item
     * @return Food Object
     */
    public Food findFoodByName(String name);

    /**
     * details of all foods available in database
     * @return - List of all foods
     */
    public List<Food> getAllFood();
}
