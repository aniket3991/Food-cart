package foodcart.controller;

import foodcart.entity.User;
import foodcart.service.Implementation.UserServiceImplementation;
import foodcart.service.UserService;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 * User Controller to handle user requests
 */
public class UserController{

    private final UserService userService;

    public UserController(){
        userService = new UserServiceImplementation();
    }

    /**
     * add a new user to the database
     * @param user - A user Object with all user details
     * @return - true/false
     */
    public boolean addUser(User user) {
        return userService.addUser(user);
    }

    /**
     * update an existed user
     * @param user - A user Object with all updated user details
     * @param email - email of the current user
     * @return - true/false
     */
    public boolean updateUser(User user, String email) {
        return userService.updateUser(user, email);
    }

    /**
     * delete an existed user
     * @param userId - user id of the current user
     * @return - true/false
     */
    public boolean deleteUser(int userId) {
        return userService.deleteUser(userId);
    }

    /**
     * return detail of a single user
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

    /**
     * Activate a deactivated user
     * @param userId - user id of current user
     * @return - true/false
     */
    public boolean activateUser(int userId) {
        return userService.activateUser(userId);
    }
}
