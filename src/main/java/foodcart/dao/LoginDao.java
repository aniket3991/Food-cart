package foodcart.dao;

import foodcart.entity.User;

public interface LoginDao {
    User login(String email);
}
