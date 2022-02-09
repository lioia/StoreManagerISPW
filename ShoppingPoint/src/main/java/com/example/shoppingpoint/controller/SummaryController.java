package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.SummaryBean;
import com.example.shoppingpoint.dao.SoldProductDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.exception.DatabaseException;
import com.example.shoppingpoint.model.ClientListData;
import com.example.shoppingpoint.model.SoldProduct;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;

import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class SummaryController {

    public HashMap<String, List<SoldProduct>> getHashSoldProducts(SummaryBean bean) throws ControllerException {

        List<SoldProduct> products = getSoldProducts();
        HashMap<String, List<SoldProduct>> filtered = new HashMap<>();

        for (SoldProduct p : products) {
            LocalDate currentDate = LocalDate.now();
            String key = p.getProduct().getName();

            switch (bean.getSelected()) {
                case "All" -> {
                    filtered.computeIfAbsent(key, k -> new ArrayList<>());
                    filtered.get(key).add(p);
                }
                case "Last Month" -> {
                    if (p.getDate().getYear() == currentDate.getYear() && p.getDate().getMonth() == currentDate.getMonth()) {
                        filtered.computeIfAbsent(key, k -> new ArrayList<>());
                        filtered.get(key).add(p);
                    }
                }
                case "Last Week" -> {
                    LocalDate mondayOfCurrentWeek = currentDate.with(previousOrSame(DayOfWeek.MONDAY));
                    LocalDate sundayOfCurrentWeek = currentDate.with(nextOrSame(DayOfWeek.SUNDAY));
                    if (p.getDate().isAfter(mondayOfCurrentWeek) && p.getDate().isBefore(sundayOfCurrentWeek)) {
                        filtered.computeIfAbsent(key, k -> new ArrayList<>());
                        filtered.get(key).add(p);
                    }
                }
                default -> throw new IllegalStateException("Unexpected value: " + bean.getSelected());
            }
        }
        return filtered;
    }

    public List<SoldProduct> getSoldProducts() throws ControllerException{
        String storeName = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName();
        List<SoldProduct> products;
        try {
            products = SoldProductDAO.getSoldProducts(storeName);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
        return products;
    }
}
