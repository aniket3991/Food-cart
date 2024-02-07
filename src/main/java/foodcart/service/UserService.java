package foodcart.service;

import foodcart.entity.User;

import java.util.List;

public interface UserService {

    public boolean addUser(User user);

    public boolean updateUser(User user);

    public boolean deleteUser(String email);

    /**
     * find details of a user
     * @param email - email address of user
     * @return - User Object
     */
    public User findUserByEmail(String email);

    /**
     * list of all available users
     * @return - Users list
     */
    public List<User> getAllUser();
}
