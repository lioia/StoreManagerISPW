package com.shoppingpoint.controller;

import com.shoppingpoint.bean.LoginBean;
import com.shoppingpoint.dao.StoreDAO;
import com.shoppingpoint.dao.UserDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.model.user.User;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public User login(LoginBean bean) throws ControllerException {
        User user;
        try {
            user = UserDAO.getUserByUsername(bean.getLoginUsername());
            if (!Objects.equals(user.getPassword(), bean.getLoginPassword())) {
                throw new ControllerException("Invalid input: password");
            }
            if(user instanceof StoreOwner) {
                ((StoreOwner)user).setStore(StoreDAO.getStoreByStoreOwnerUsername(user.getUsername()));
            }
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
        return user;
    }
}
