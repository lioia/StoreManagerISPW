package com.example.shoppingpoint.controller;

import com.example.shoppingpoint.utils.ClientListData;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;

import java.util.List;

public class ClientListController {
    public List<ClientListData> getClientFromStore(String storeName) throws Exception {
        return LoyaltyCardDAO.getClientFromStoreName(storeName);
    }
}
