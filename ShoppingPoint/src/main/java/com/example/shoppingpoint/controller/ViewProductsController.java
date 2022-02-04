package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.Store;
import com.example.shoppingpoint.model.product.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewProductsController {
    public List<GenericProduct> getProductsFromStore(Store store) throws ControllerException {
        List<Product> products;
        try {
            products = ProductDAO.getProductsFromStore(store.getName());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }

        List<GenericProduct> genericProducts = new ArrayList<>();
        for (Product product : products) {
            genericProducts.add(new ProductAdapter(product));
        }

        return genericProducts;
    }
}
