package foodcart.controller;

import foodcart.entity.Cart;
import foodcart.entity.Food;
import foodcart.service.CartService;
import foodcart.service.Implementation.CartServiceImplementation;

import java.util.List;

public class CartController {

    private CartService cartService = new CartServiceImplementation();

    /**
     * add new food to the cart
     * @param food food object
     */
    public boolean addToCart(Food food, Cart cart){
        return cartService.addToCart(food, cart);
    }

    public boolean saveCart(Cart cart){
        return cartService.saveCart(cart);
    }

    public boolean moveCartToOrder(Cart cart){
        return cartService.moveCartToOrder(cart);
    }
    public List<Food> loadToCart(int userId){
        return cartService.loadToCart(userId);
    }
}
