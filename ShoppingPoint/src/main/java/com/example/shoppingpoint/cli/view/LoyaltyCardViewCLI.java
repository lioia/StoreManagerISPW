package com.example.shoppingpoint.cli.view;

import com.example.shoppingpoint.bean.store_dashboard.LoyaltyCardBean;
import com.example.shoppingpoint.cli.utils.CLIReader;
import com.example.shoppingpoint.exception.BeanException;

import java.io.IOException;
import java.util.List;

public class LoyaltyCardViewCLI {
    public LoyaltyCardBean detailsInput() throws IOException, BeanException {
        System.out.println("How many points are equivalent to 1€ discount?");
        String pointsInEuro = CLIReader.readline();
        System.out.println("How many euros spent are equivalent to 1 point?");
        String euroInPoints = CLIReader.readline();
        return new LoyaltyCardBean(true,pointsInEuro,euroInPoints);

    }

    public int getChoiceIfActive() throws IOException{
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "update loyalty card details",
                "deactivate loyalty card",
                "Go back"));
    }

    //TODO meglio una funzione per prendere si o no?
    public  int getChoiceIfNotActive() throws IOException{
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "activate loyalty card",
                "Go back"));
    }
}
