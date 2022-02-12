package com.shoppingpoint.controller;

import com.shoppingpoint.bean.RegisterBean;
import com.shoppingpoint.dao.StoreDAO;
import com.shoppingpoint.dao.UserDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.exception.DatabaseException;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.model.user.User;

import java.sql.SQLException;

public class RegisterController {
    public User register(RegisterBean bean) throws ControllerException {
        try {
            UserDAO.saveUser(bean.getRegisterUsername(), bean.getEmail(), bean.getRegisterPassword(), bean.getUserType());
            User user = UserDAO.getUserByUsername(bean.getRegisterUsername());
            if(user instanceof StoreOwner) {
                ((StoreOwner)user).setStore(StoreDAO.getStoreByStoreOwnerUsername(user.getUsername()));
            }
            return user;
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }
}
