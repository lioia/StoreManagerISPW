package com.example.shoppingpoint.cli.graphic_controller;

import com.example.shoppingpoint.bean.SummaryBean;
import com.example.shoppingpoint.cli.view.SummaryViewCLI;
import com.example.shoppingpoint.controller.SummaryController;
import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.exception.ControllerException;
import com.example.shoppingpoint.model.SoldProduct;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SummaryGraphicControllerCLI {
    public void initialize() throws BeanException, ControllerException, IOException {
        SummaryViewCLI view = new SummaryViewCLI();
        SummaryController controller = new SummaryController();
        SummaryBean bean = new SummaryBean("All");
        boolean exit = false;
        while (!exit) {
            HashMap<String, List<SoldProduct>> soldProducts = controller.getHashSoldProducts(bean);
            view.showChart(soldProducts, bean);
            int action = view.getChoice();
            switch (action) {
                // Change filter type
                case 1 -> bean = view.changeChartFilter();
                // List View
                case 2 -> {
                    SoldProductsListGraphicControllerCLI soldProductsListGRaphicControllerCLI = new SoldProductsListGraphicControllerCLI();
                    soldProductsListGRaphicControllerCLI.initialize();
                }
                // Go back
                case 3 -> exit = true;
            }
        }
    }
}
