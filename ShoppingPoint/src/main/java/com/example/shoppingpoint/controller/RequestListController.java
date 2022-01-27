package com.example.shoppingpoint.controller;
import java.util.List;
import com.example.shoppingpoint.model.Request;
import com.example.shoppingpoint.dao.RequestDAO;
import com.example.shoppingpoint.model.product.Product;
import com.example.shoppingpoint.dao.ProductDAO;

public class RequestListController {
    public List<Request> getRequest()throws Exception{
        List<Request> requestList = RequestDAO.getAllRequests();
        return requestList;
    }
    public Product getProduct(int productId)throws Exception{
        return ProductDAO.getProductById(productId);
    }

}
