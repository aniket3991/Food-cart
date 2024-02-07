package foodcart.service.Implementation;

import foodcart.dao.FoodDao;
import foodcart.dao.Implementation.FoodDaoImplementation;
import foodcart.entity.Food;
import foodcart.service.FoodService;

import java.util.List;

public class FoodServiceImplementation implements FoodService {

    private FoodDao foodDao = new FoodDaoImplementation();

    /**
     * add new food details into database
     * @param food new Food details
     * @return true/false
     */
    @Override
    public boolean addFood(Food food) {
        return foodDao.addFood(food);
    }

    /**
     * update food details with new data
     * @param food - Food Object
     * @return true/false
     */
    @Override
    public boolean updateFood(Food food, String name) {
        return foodDao.updateFood(food, name);
    }

    /**
     * delete the food item details form database
     * @param name - name of the food
     * @return true/false
     */
    @Override
    public boolean deleteFood(String name) {
        return foodDao.deleteFood(name);
    }

    /**
     * detail of single food item
     * @param name - name of the food
     * @return food object
     */
    @Override
    public Food findFoodByName(String name) {
        return foodDao.findFoodByName(name);
    }

    /**
     * details of all foods available in database
     * @return - List of all foods
     */
    @Override
    public List<Food> getAllFood() {
        return foodDao.getAllFood();
    }
}
