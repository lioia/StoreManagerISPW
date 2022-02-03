package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.user.User;

import java.sql.SQLException;

public class RegisterController {
    public User register(RegisterBean bean) throws ControllerException {
        try {
            UserDAO.saveUser(bean.getRegisterUsername(), bean.getEmail(), bean.getRegisterPassword(), bean.getUserType());
            return UserDAO.getUserByUsername(bean.getRegisterUsername());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }
}
