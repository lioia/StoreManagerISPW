package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.add_product.AddProductBean;
import com.example.shoppingpoint.bean.add_product.AddProductCommonBean;
import com.example.shoppingpoint.dao.ProductDAO;
import com.example.shoppingpoint.model.user.StoreOwner;
import com.example.shoppingpoint.singleton.LoggedInUser;
import com.example.shoppingpoint.utils.ProductType;

public class AddProductController {
    public void saveProduct(ProductType type, AddProductBean bean, AddProductCommonBean commonBean) throws Exception {
        switch (type) {
            case CLOTHES -> ProductDAO.saveClothesProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getSize(), bean.getMaterial());
            case SHOES -> ProductDAO.saveShoesProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getSize(), bean.getMaterial(), bean.getShoesType());
            case BOOK -> ProductDAO.saveBookProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getAuthor(), bean.getPlot(), bean.getGenre());
            case COMICS -> ProductDAO.saveComicsProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getAuthor(), bean.getArtist(), bean.getPlot(), bean.getGenre(), bean.getVolumeNumber());
            case VIDEOGAME -> ProductDAO.saveVideoGameProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getPlot(), bean.getGenre(), bean.getConsoleType());
            case GAMECONSOLE -> ProductDAO.saveGameConsoleProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getConsoleType(), bean.isDigitalOnly());
            case COMPUTER -> ProductDAO.saveComputerProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getComputerType(), bean.getRam(), bean.getSsd(), bean.getBatterySize(), bean.getCpu(), bean.getGpu(), bean.getBrand(), bean.getDisplaySize());
            case HOMEAPPLIANCES -> ProductDAO.saveHomeApplianceProduct(commonBean.getName(), commonBean.getPrice(), commonBean.getDiscountedPrice(), commonBean.getQuantity(), commonBean.getStatus(), ((StoreOwner) LoggedInUser.getInstance().getUser()).getStore().getName(), bean.getEnergyClass(), bean.getSpecs());
            default -> throw new Exception("Unsupported type");
        }
    }
}
