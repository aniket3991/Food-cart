package foodcart.service.Implementation;

import foodcart.dao.CartDao;
import foodcart.dao.Implementation.CartDaoImplementation;
import foodcart.entity.Cart;
import foodcart.entity.Food;
import foodcart.service.CartService;
import foodcart.service.FoodService;

import java.util.List;

public class CartServiceImplementation implements CartService {

    private FoodService foodService = new FoodServiceImplementation();
    private CartDao cartDao = new CartDaoImplementation();

    @Override
    public boolean addToCart(Food food, Cart cart) {
        Food existedFood = foodService.findFoodByName(food.getName());

        for (Food cartFood : cart.getFoodList()) {
            if (cartFood.getName().equals(food.getName())) {
                if ((cartFood.getQuantity() + food.getQuantity()) > existedFood.getQuantity()) {
                    System.out.println("Already " + cartFood.getQuantity() + " pieces of same item" +
                            " available in the cart, now you can add only up to " +
                            (existedFood.getQuantity() - cartFood.getQuantity()) + " piece");
                    return false;
                }
                cartFood.setQuantity(cartFood.getQuantity() + food.getQuantity());
                return true;
            }
        }

        cart.getFoodList().add(food);
        return true;
    }

    @Override
    public boolean saveCart(Cart cart) {
        return cartDao.saveCart(cart);
    }

    @Override
    public boolean moveCartToOrder(Cart cart) {
        return cartDao.moveCartToOrder(cart);
    }

    @Override
    public List<Food> loadToCart(int userId) {
        return cartDao.loadToCart(userId);
    }
}
