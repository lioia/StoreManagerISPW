package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class StoreController {
    public List<GenericProduct> getProductsFromStore(StoreBean bean) throws Exception {
        List<Product> products = ProductDAO.getProductsFromStore(bean.getStoreName());
        List<Product> filteredProducts = new ArrayList<>();
        if(bean.getSearchQuery().isEmpty()) filteredProducts = products;
        else {
            for(Product product : products) {
                if(product.getName().toLowerCase().contains(bean.getSearchQuery().toLowerCase()))
                    filteredProducts.add(product);
            }
        }

        List<GenericProduct> genericProducts = new ArrayList<>();
        for(Product product : filteredProducts) {
            genericProducts.add(new ProductAdapter(product));
        }

        return genericProducts;
    }
}
