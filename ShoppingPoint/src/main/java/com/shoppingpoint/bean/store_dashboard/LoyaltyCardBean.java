package com.shoppingpoint.bean.store_dashboard;

import com.shoppingpoint.exception.BeanException;

public class LoyaltyCardBean {
    private boolean active;
    private Integer pointsInEuro;
    private Integer euroInPoints;

    public LoyaltyCardBean(boolean active, String pointsInEuro, String euroInPoints) throws BeanException {
        this(active, Integer.parseInt(pointsInEuro), Integer.parseInt(euroInPoints));
    }

    public LoyaltyCardBean(boolean active, Integer pointsInEuro, Integer euroInPoints) throws BeanException {
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

    public void setPointsInEuro(Integer pointsInEuro) throws BeanException {
        if (pointsInEuro < 0) throw new BeanException("points in euro", BeanException.MORE_THAN_ZERO_REASON);
        if (active)
            this.pointsInEuro = pointsInEuro;
        else this.pointsInEuro = 0;
    }

    public Integer getEuroInPoints() {
        return euroInPoints;
    }

    public void setEuroInPoints(Integer euroInPoints) throws BeanException {
        if (euroInPoints < 0) throw new BeanException("euro in points", BeanException.MORE_THAN_ZERO_REASON);
        if (active)
            this.euroInPoints = euroInPoints;
        else this.euroInPoints = 0;
    }
}
