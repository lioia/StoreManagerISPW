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
        return new LoyaltyCardBean(true, pointsInEuro, euroInPoints);

    }

    public int getChoiceIfActive() throws IOException {
        System.out.println("--------------------");
        System.out.println("Loyalty Card Information");
        System.out.println("What do you want to do?");
        return CLIReader.multiChoice(List.of(
                "Update loyalty card details",
                "Deactivate loyalty card",
                "Go back"));
    }

    public boolean getChoiceIfNotActive() throws IOException {
        System.out.println("Loyalty Card Information");
        System.out.println("What do you want to do?");
        return CLIReader.yesOrNo("Activate loyalty card? [y/n]");
    }

    public void viewCardDetails(int pointsInEuro, int eurosInPoint) {
        System.out.printf("%d points spent = 1€ discount%n%d€ spent = 1 point earned", pointsInEuro, eurosInPoint);
    }
}
