package foodcart.dao;

import foodcart.entity.Cart;
import foodcart.entity.Food;

import java.util.List;

public interface CartDao {

    boolean saveCart(Cart cart);

    boolean moveCartToOrder(Cart cart);

    List<Food> loadToCart(int userId);
}
