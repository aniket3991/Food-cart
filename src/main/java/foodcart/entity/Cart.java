package foodcart.entity;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 */
public class Cart {

    private int userId;
    private final List<Food> foodList;

    public Cart(int userId, List<Food> foodList) {
        this.userId = userId;
        this.foodList = foodList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}
