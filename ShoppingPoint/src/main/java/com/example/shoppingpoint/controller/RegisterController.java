package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.RegisterBean;
import com.example.shoppingpoint.dao.UserDAO;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.user.User;

import java.sql.SQLException;

public class RegisterController {
    public User register(RegisterBean bean) throws SQLException, DatabaseException {
        UserDAO.saveUser(bean.getRegisterUsername(), bean.getEmail(), bean.getRegisterPassword(), bean.getUserType());
        return UserDAO.getUserByUsername(bean.getRegisterUsername());
    }
}
