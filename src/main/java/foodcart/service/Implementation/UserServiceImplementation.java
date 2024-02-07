package foodcart.service.Implementation;

import foodcart.dao.Implementation.UserDaoImplementation;
import foodcart.dao.UserDao;
import foodcart.entity.User;
import foodcart.service.UserService;

import java.util.List;

public class UserServiceImplementation implements UserService {

    private UserDao userDao = new UserDaoImplementation();

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
        return userDao.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
