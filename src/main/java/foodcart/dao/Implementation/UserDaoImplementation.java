package foodcart.dao.Implementation;

import foodcart.dao.UserDao;
import foodcart.entity.User;
import foodcart.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplementation implements UserDao {

    private final Connection connection;

    public UserDaoImplementation(){
        connection = DBConnection.establishConnection();
    }

    /**
     * add a new user to the database
     * @param user - A user Object with all user details
     * @return - true/false
     */
    @Override
    public boolean addUser(User user) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("insert into " +
                    "tbl_user(first_name,last_name,role,email,password,city,activated_user)" +
                    " values(?,?,?,?,?,?,?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setInt(7, user.getIsActivated());

            if (preparedStatement.executeUpdate() > 0)
                return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * update an existed user
     * @param user - A user Object with all updated user details
     * @param email - email of the current user
     * @return - true/false
     */
    @Override
    public boolean updateUser(User user, String email) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement =
                    connection.prepareStatement("update tbl_user " +
                            "set first_name=?,last_name=?,role=?,email=?,password=?,city=? where email=?");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setString(7, email);

            if (preparedStatement.executeUpdate() > 0)
                return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * delete an existed user
     * @param userId - user id of the current user
     * @return - true/false
     */
    @Override
    public boolean deleteUser(int userId) {

        PreparedStatement deleteFromCart = null;
        PreparedStatement deactivateUser = null;

        try {
            deleteFromCart = connection.prepareStatement("delete from tbl_cart where user_id=?");
            deleteFromCart.setInt(1, userId);

            deactivateUser = connection.prepareStatement("update tbl_user " +
                    "set activated_user=? where user_id=?");
            deactivateUser.setInt(1, 0);
            deactivateUser.setInt(2, userId);

            deleteFromCart.executeUpdate();

            if (deactivateUser.executeUpdate() > 0)
                return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {

                if (deleteFromCart != null)
                    deleteFromCart.close();

                if (deactivateUser != null)
                    deactivateUser.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * find details of a user
     * @param email - email address of user
     * @return - User Object
     */
    @Override
    public User findUserByEmail(String email) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement("select * from tbl_user where email=?");
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("city"), resultSet.getInt("activated_user"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return user;
    }

    /**
     * list of all available users
     * @return - Users list
     */
    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from tbl_user where activated_user=?");
            preparedStatement.setInt(1, 1);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("city"), resultSet.getInt("activated_user"));
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return userList;
    }

    /**
     * Activate a deactivated user
     * @param userId - user id of current user
     * @return - true/false
     */
    @Override
    public boolean activateUser(int userId) {
        PreparedStatement activateUser = null;
        try {
            activateUser = connection.prepareStatement("update tbl_user set activated_user=? where user_id=?");
            activateUser.setInt(1, 1);
            activateUser.setInt(2, userId);

            return activateUser.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (activateUser != null)
                    activateUser.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
