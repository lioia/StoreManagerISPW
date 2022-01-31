package com.example.shoppingpoint.bean;

import com.example.shoppingpoint.exception.BeanException;

public class OrdersBean {
    private float value;

    public OrdersBean(float value) throws BeanException {
        setValue(value);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) throws BeanException {
        if (value < 0) throw new BeanException("review", "it has to be more than 0");
        if (value > 5) throw new BeanException("review", "it has to be less than 5");
        this.value = value;
    }
}
