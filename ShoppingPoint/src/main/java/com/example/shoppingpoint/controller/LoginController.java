package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.LoginBean;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.exception.InvalidInputException;
import com.example.shoppingpoint.model.user.User;

import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    public User login(LoginBean bean) throws SQLException, DatabaseException, InvalidInputException {
        User user = UserDAO.getUserByUsername(bean.getUsername());
        if (!Objects.equals(user.getPassword(), bean.getPassword())) {
            throw new InvalidInputException("password");
        }
        return user;
    }
}
