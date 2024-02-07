package foodcart.service;

import foodcart.entity.Cart;
import foodcart.entity.Food;

import java.util.List;

public interface CartService {

    /**
     * add new food to cart
     * @param food Food item
     */
    boolean addToCart(Food food, Cart cart);

    boolean saveCart(Cart cart);

    boolean moveCartToOrder(Cart cart);

    List<Food> loadToCart(int userId);
}