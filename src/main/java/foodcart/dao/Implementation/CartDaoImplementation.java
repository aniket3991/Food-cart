package foodcart.dao.Implementation;

import foodcart.dao.CartDao;
import foodcart.entity.Cart;
import foodcart.entity.Food;
import foodcart.utility.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class CartDaoImplementation implements CartDao {

    @Override
    public boolean saveCart(Cart cart) {
        Connection connection = DBConnection.establishConnection();
        PreparedStatement preparedStatement = null;
        PreparedStatement deleteStatement = null;
        boolean isItemAvailableInCart = false;

        try {
            deleteStatement = connection.prepareStatement("delete from tbl_cart where user_id=?");
            deleteStatement.setInt(1, cart.getUserId());
            deleteStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(
                    "insert into tbl_cart(food_id, quantity, user_id) values(?,?,?)");
            for (Food food : cart.getFoodList()) {
                isItemAvailableInCart = true;
                preparedStatement.setInt(1, food.getFoodId());
                preparedStatement.setInt(2, food.getQuantity());
                preparedStatement.setInt(3, cart.getUserId());

                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (deleteStatement != null)
                    deleteStatement.close();

                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isItemAvailableInCart;
    }

    @Override
    public List<Food> loadToCart(int userId) {
        List<Food> foodListInCart = new LinkedList<>();
        Connection connection = DBConnection.establishConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ResultSet finalResultSet = null;

        try {
            preparedStatement = connection.prepareStatement("select food_id,quantity" +
                    " from tbl_cart where user_id=?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                preparedStatement = connection.prepareStatement("select name,price,quantity" +
                        " from tbl_food where food_id=?");
                preparedStatement.setInt(1, resultSet.getInt("food_id"));
                finalResultSet = preparedStatement.executeQuery();
                int quantityToCart;
                if (finalResultSet.next() && finalResultSet.getInt("quantity") > 0) {
                    if (finalResultSet.getInt("quantity") >= resultSet.getInt("quantity")) {
                        quantityToCart = resultSet.getInt("quantity");
                    } else {
                        quantityToCart = finalResultSet.getInt("quantity");
                    }
                    foodListInCart.add(new Food(resultSet.getInt("food_id"),
                            finalResultSet.getString("name"),
                            finalResultSet.getDouble("price"),
                            quantityToCart, ""));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

                if (finalResultSet != null)
                    finalResultSet.close();

                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foodListInCart;
    }

    @Override
    public boolean moveCartToOrder(Cart cart) {
        Connection connection = DBConnection.establishConnection();
        PreparedStatement deleteStatement = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement fetchQuantity = null;
        PreparedStatement changeQuantity = null;
        ResultSet availableQuantityResultSet = null;

        try {
            deleteStatement = connection.prepareStatement("delete from tbl_cart where user_id=?");
            deleteStatement.setInt(1, cart.getUserId());
            deleteStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(
                    "insert into tbl_order(user_id, food_id, order_date, order_status, price, quantity)" +
                            " values(?,?,?,?,?,?)");
            fetchQuantity = connection.prepareStatement("select quantity from tbl_food where food_id=?");

            changeQuantity = connection.prepareStatement("update tbl_food set quantity=? where food_id=?");

            for (Food food : cart.getFoodList()) {
                fetchQuantity.setInt(1, food.getFoodId());
                availableQuantityResultSet = fetchQuantity.executeQuery();
                availableQuantityResultSet.next();

                changeQuantity.setInt(1, availableQuantityResultSet.getInt("quantity") - food.getQuantity());

                changeQuantity.setInt(2, food.getFoodId());
                changeQuantity.executeUpdate();

                preparedStatement.setInt(1, cart.getUserId());
                preparedStatement.setInt(2, food.getFoodId());
                preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(4, "Ordered");
                preparedStatement.setDouble(5, food.getPrice());
                preparedStatement.setInt(6, food.getQuantity());

                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();

                if (deleteStatement != null)
                    deleteStatement.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
