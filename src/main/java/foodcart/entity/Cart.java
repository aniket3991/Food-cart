package foodcart.entity;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 */
public class Cart {

    private int cartId;
    private int userId;
    private List<Food> foodList;

    public Cart(int userId, List<Food> foodList) {
        this.userId = userId;
        this.foodList = foodList;
    }

    public Cart(int cartId, int userId, List<Food> foodList) {
        this(userId, foodList);
        this.cartId = cartId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
