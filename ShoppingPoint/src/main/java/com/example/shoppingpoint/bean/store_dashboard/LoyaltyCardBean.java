package com.example.shoppingpoint.bean.store_dashboard;

public class LoyaltyCardBean {
    private boolean active;
    private Integer pointsInEuro;
    private Integer euroInPoints;

    public LoyaltyCardBean(boolean active, String pointsInEuro, String euroInPoints) {
        this(active, Integer.parseInt(pointsInEuro), Integer.parseInt(euroInPoints));
    }

    public LoyaltyCardBean(boolean active, Integer pointsInEuro, Integer euroInPoints) {
        setActive(active);
        setPointsInEuro(pointsInEuro);
        setEuroInPoints(euroInPoints);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getPointsInEuro() {
        return pointsInEuro;
    }

    public void setPointsInEuro(Integer pointsInEuro) {
        if (pointsInEuro < 0) {
//            TODO throw exception
            System.out.println("Invalid data");
        }
        if (active)
            this.pointsInEuro = pointsInEuro;
        else this.pointsInEuro = 0;
    }

    public Integer getEuroInPoints() {
        return euroInPoints;
    }

    public void setEuroInPoints(Integer euroInPoints) {
        if (euroInPoints < 0) {
//            TODO throw exception
            System.out.println("Invalid data");
        }
        if (active)
            this.euroInPoints = euroInPoints;
        else this.euroInPoints = 0;
    }
}
