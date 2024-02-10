package foodcart.service;

import foodcart.entity.User;

import java.util.List;

/**
 * Author: Aniket Kumar Mishra
 * User services to handle services related to admin-user requests
 */
public interface UserService {
    /**
     * add a new user to the database
     * @param user - A user Object with all user details
     * @return - true/false
     */
    boolean addUser(User user);

    /**
     * update an existed user
     * @param user - A user Object with all updated user details
     * @param email - email of the current user
     * @return - true/false
     */
    boolean updateUser(User user, String email);

    /**
     * delete an existed user
     * @param userId - user id of the current user
     * @return - true/false
     */
    boolean deleteUser(int userId);

    /**
     * find details of a user
     * @param email - email address of user
     * @return - User Object
     */
    User findUserByEmail(String email);

    /**
     * list of all available users
     * @return - Users list
     */
    List<User> getAllUser();

    /**
     * Activate a deactivated user
     * @param userId - user id of current user
     * @return - true/false
     */
    boolean activateUser(int userId);
}
