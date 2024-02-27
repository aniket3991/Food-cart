package foodcart.service.Implementation;

import foodcart.dao.Implementation.UserDaoImplementation;
import foodcart.dao.UserDao;
import foodcart.entity.User;
import foodcart.service.UserService;

import java.util.List;

public class UserServiceImplementation implements UserService {

    private final UserDao userDao;

    public UserServiceImplementation(){
        userDao = new UserDaoImplementation();
    }
    /**
     * add a new user to the database
     * @param user - A user Object with all user details
     * @return - true/false
     */
    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * update an existed user
     * @param user - A user Object with all updated user details
     * @param email - email of the current user
     * @return - true/false
     */
    @Override
    public boolean updateUser(User user, String email) {
        return userDao.updateUser(user, email);
    }

    /**
     * delete an existed user
     * @param userId - user id of the current user
     * @return - true/false
     */
    @Override
    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    /**
     * find details of a user
     * @param email - email address of user
     * @return - User Object
     */
    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    /**
     * list of all available users
     * @return - Users list
     */
    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    /**
     * Activate a deactivated user
     * @param userId - user id of current user
     * @return - true/false
     */
    @Override
    public boolean activateUser(int userId) {
        return userDao.activateUser(userId);
    }
}
