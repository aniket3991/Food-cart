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

    private Connection connection = DBConnection.establishConnection();

    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        return false;
    }

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
                        resultSet.getString("city"));
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
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from tbl_user");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("city"));
                userList.add(user);
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
        return userList;
    }
}
