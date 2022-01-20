package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.adapter.GenericProduct;
import com.example.shoppingpoint.adapter.ProductAdapter;
import com.example.shoppingpoint.bean.StoreBean;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreDashboardController {
    public Store getStoreFromStoreOwnerName(String storeOwner) throws Exception {
        return StoreDAO.getStoreByStoreOwnerUsername(storeOwner);
    }

    public List<GenericProduct> getProductsFromStore(Store store) throws Exception {
        List<Product> products = ProductDAO.getProductsFromStore(store.getName());


        List<GenericProduct> genericProducts = new ArrayList<>();
        for(Product product : products) {
            genericProducts.add(new ProductAdapter(product));
        }

        return genericProducts;
    }
}
