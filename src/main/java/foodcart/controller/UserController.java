package foodcart.controller;

import foodcart.entity.User;
import foodcart.service.Implementation.UserServiceImplementation;
import foodcart.service.UserService;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 */
public class UserController{

    private UserService userService = new UserServiceImplementation();

    public boolean addUser(User user) {
        return false;
    }

    public boolean updateUser(User user) {
        return false;
    }

    public boolean deleteUser(String email) {
        return false;
    }

    /**
     * return detail of single user
     * @param email - email of single user
     * @return - Object of User
     */
    public User findUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    /**
     * return list of all available users
     * @return - User List
     */
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}
