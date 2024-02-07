package foodcart.dao.Implementation;

import foodcart.dao.FoodDao;
import foodcart.entity.Food;
import foodcart.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImplementation implements FoodDao {
    private Connection connection = DBConnection.establishConnection();

    /**
     * add new food details into database
     *
     * @param food new Food details
     * @return true/false
     */
    @Override
    public boolean addFood(Food food) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement("insert into tbl_food(name,price,quantity,description)" +
                            " values(?,?,?,?)");
            preparedStatement.setString(1, food.getName());
            preparedStatement.setDouble(2, food.getPrice());
            preparedStatement.setInt(3, food.getQuantity());
            preparedStatement.setString(4, food.getDescription());

            int row = preparedStatement.executeUpdate();

            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * update details of an existed food item
     *
     * @param food
     * @return
     */
    @Override
    public boolean updateFood(Food food, String name) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement("update tbl_food set name=?, price=?, quantity=?," +
                            "description=? where name=?");

            preparedStatement.setString(1, food.getName());
            preparedStatement.setDouble(2, food.getPrice());
            preparedStatement.setInt(3, food.getQuantity());
            preparedStatement.setString(4, food.getDescription());
            preparedStatement.setString(5, name);

            int row = preparedStatement.executeUpdate();

            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * delete single food item details form database
     * @param name - name of the food item
     * @return true/false
     */
    @Override
    public boolean deleteFood(String name) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from tbl_food where name=?");

            preparedStatement.setString(1, name);

            int row = preparedStatement.executeUpdate();

            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Food findFoodByName(String name) {
        Food food = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement("select * from tbl_food where name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                food = new Food(resultSet.getInt("food_id"), resultSet.getString("name"),
                        resultSet.getDouble("price"), resultSet.getInt("quantity"),
                        resultSet.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return food;
    }

    /**
     * details of all foods available in database
     * @return - List of all foods
     */
    @Override
    public List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from tbl_food");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Food food = new Food(resultSet.getInt("food_id"), resultSet.getString("name"),
                        resultSet.getDouble("price"), resultSet.getInt("quantity"),
                        resultSet.getString("description"));
                foodList.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foodList;
    }
}