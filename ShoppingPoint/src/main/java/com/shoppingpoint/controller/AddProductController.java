package com.shoppingpoint.controller;

import com.shoppingpoint.bean.add_product.AddProductBean;
import com.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.shoppingpoint.dao.ProductDAO;
import com.shoppingpoint.exception.ControllerException;
import com.shoppingpoint.factory.ProductFactory;
import com.shoppingpoint.model.product.Product;
import com.shoppingpoint.model.user.StoreOwner;
import com.shoppingpoint.singleton.LoggedInUser;
import com.shoppingpoint.utils.ProductType;

import java.sql.SQLException;

public class AddProductController {
    public void saveProduct(ProductType type, AddProductBean bean, AddProductCommonBean commonBean) throws ControllerException {
        ProductFactory factory = new ProductFactory();
        String storeName = ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName();
        Product product = factory.createProduct(type, 0, commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(),
                commonBean.getStatus(), commonBean.getImage(), storeName,
                bean.getSize(), bean.getMaterial(), bean.getShoesType(), bean.getAuthor(), bean.getArtist(), bean.getPlot(), bean.getGenre(),
                bean.getVolumeNumber(), bean.getConsoleType(), bean.isDigitalOnly(), bean.getComputerType(), bean.getRam(), bean.getSsd(), bean.getCpu(),
                bean.getGpu(), bean.getBatterySize(), bean.getDisplaySize(), bean.getBrand(), bean.getEnergyClass(), bean.getSpecs());

        try {
            ProductDAO.saveProduct(product);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }
    }
}
