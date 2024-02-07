package foodcart.dao.Implementation;

import foodcart.dao.LoginDao;
import foodcart.entity.User;
import foodcart.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoImplementation implements LoginDao {

    private Connection connection = DBConnection.establishConnection();

    @Override
    public User login(String email) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "select * from tbl_user where email=?");
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("city"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return user;
    }
}
