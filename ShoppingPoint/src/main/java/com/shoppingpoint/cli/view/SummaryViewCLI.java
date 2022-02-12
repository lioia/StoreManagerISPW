package com.shoppingpoint.cli.view;

import com.shoppingpoint.bean.SummaryBean;
import com.shoppingpoint.cli.utils.CLIReader;
import com.shoppingpoint.exception.BeanException;
import com.shoppingpoint.model.SoldProduct;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SummaryViewCLI {
    public void showChart(Map<String, List<SoldProduct>> soldProducts, SummaryBean bean) {
        System.out.println("--------------------");
        System.out.println("Sold Products");
        System.out.println(bean.getSelected());
        for (String key : soldProducts.keySet()) {
            System.out.println(key);
            for (SoldProduct product : soldProducts.get(key)) {
                System.out.printf("\tDate: %s: ", product.getDate().toString());
                for (int i = 0; i < product.getQuantity(); i++)
                    System.out.print("⬛");
                System.out.printf(" (%d)%n", product.getQuantity());
            }
        }
    }

    public int getChoice() throws IOException {
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Change filter type",
                "List View",
                "Go Back"
        ));
    }

    public SummaryBean changeChartFilter() throws IOException, BeanException {
        System.out.println("Change filter type: ");
        String choice = CLIReader.multiChoiceString(List.of("All", "Last Month", "Last Week"));
        return new SummaryBean(choice);
    }
}
