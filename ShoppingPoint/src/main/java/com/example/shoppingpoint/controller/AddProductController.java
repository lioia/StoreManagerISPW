package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.add_product.AddProductBean;
import com.example.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.factory.ProductFactory;
import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ProductType;

public class AddProductController {
    public void saveProduct(ProductType type, AddProductBean bean, AddProductCommonBean commonBean) throws Exception {
        ProductFactory factory = new ProductFactory();
        String storeName = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName();
        Product product = factory.createProduct(type, 0, commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(),
                commonBean.getStatus(), commonBean.getImage(), storeName,
                bean.getSize(), bean.getMaterial(), bean.getShoesType(), bean.getAuthor(), bean.getArtist(), bean.getPlot(), bean.getGenre(),
                bean.getVolumeNumber(), bean.getConsoleType(), bean.isDigitalOnly(), bean.getComputerType(), bean.getRam(), bean.getSsd(), bean.getCpu(),
                bean.getGpu(), bean.getBatterySize(), bean.getDisplaySize(), bean.getBrand(), bean.getEnergyClass(), bean.getSpecs());

        ProductDAO.saveProduct(product);
    }
}
