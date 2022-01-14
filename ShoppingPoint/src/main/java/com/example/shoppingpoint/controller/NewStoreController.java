package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.bean.NewStoreBean;
import com.example.shoppingpoint.dao.StoreDAO;
import com.example.shoppingpoint.model.Store;

public class NewStoreController {
    public Store register(NewStoreBean bean, String storeOwner) throws Exception{
        StoreDAO.saveStore(bean.getName(), bean.getAddress(),bean.getStoreType(),storeOwner);
        return StoreDAO.getStoreByName(bean.getName());
    }
}
