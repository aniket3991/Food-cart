package foodcart.dao;

import foodcart.entity.User;

import java.util.List;

public interface UserDao {

    public boolean addUser(User user);

    public boolean updateUser(User user);

    public boolean deleteUser(String email);

    public User findUserByEmail(String email);

    public List<User> getAllUser();
}
