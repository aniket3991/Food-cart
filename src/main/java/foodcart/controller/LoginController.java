package foodcart.controller;

import foodcart.entity.User;
import foodcart.service.Implementation.LoginServiceImplementation;
import foodcart.service.LoginService;

public class LoginController {
    private LoginService loginService = new LoginServiceImplementation();

    public User login(String email) {
        return loginService.login(email);
    }
}
