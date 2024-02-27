package foodcart.dao.Implementation;

import foodcart.dao.LoginDao;
import foodcart.entity.User;
import foodcart.utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoImplementation implements LoginDao {

    private final Connection connection;

    public LoginDaoImplementation(){
        connection = DBConnection.establishConnection();
    }

    @Override
    public User login(String email) {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "select * from tbl_user where email=? and activated_user=?");
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2,1);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(resultSet.getInt("user_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("role"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("city"), resultSet.getInt("activated_user"));
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
