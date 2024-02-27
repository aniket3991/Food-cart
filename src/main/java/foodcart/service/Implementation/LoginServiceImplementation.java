package foodcart.service.Implementation;

import foodcart.dao.Implementation.LoginDaoImplementation;
import foodcart.dao.LoginDao;
import foodcart.entity.User;
import foodcart.service.LoginService;


public class LoginServiceImplementation implements LoginService {
    private final LoginDao loginDao;

    public LoginServiceImplementation(){
        loginDao = new LoginDaoImplementation();
    }

    @Override
    public User login(String email) {
        return loginDao.login(email);
    }
}
