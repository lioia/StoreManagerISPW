package com.example.shoppingpoint.controller;
import com.example.shoppingpoint.model.user.Client;
import com.example.shoppingpoint.utils.datiClientList;
import com.example.shoppingpoint.dao.LoyaltyCardDAO;
import java.util.List;
public class ClientListController {
    public List<datiClientList> getClientFromStore(String storeName)throws Exception{
        List<datiClientList> clients = LoyaltyCardDAO.getClientFromStoreName(storeName);
        return clients;



    }
}
