package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.exception.InvalidInputException;
import com.example.shoppingpoint.model.user.User;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public User login(LoginBean bean) throws ControllerException {
        User user;
        try {
            user = UserDAO.getUserByUsername(bean.getUsername());
            if (!Objects.equals(user.getPassword(), bean.getPassword())) {
                throw new ControllerException("Invalid input: password");
            }
        } catch (SQLException e) {
            throw new ControllerException("SQL Error", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database Error", e);
        }
        return user;
    }
}
