package foodcart.dao;

import foodcart.entity.User;

public interface LoginDao {
    public User login(String email);
}
