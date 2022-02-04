package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.model.user.User;

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
