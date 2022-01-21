package com.example.shoppingpoint.bean;

public class OrdersBean {
    private float value;

    public OrdersBean(float value){
        setValue(value);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        if(value < 0 || value > 5) {
//            TODO exception
            System.out.println("Invalid data");
        }
        this.value = value;
    }
}
