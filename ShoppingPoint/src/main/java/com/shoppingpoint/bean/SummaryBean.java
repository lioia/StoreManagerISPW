package com.shoppingpoint.bean;

import com.shoppingpoint.exception.BeanException;

public class SummaryBean {
    private String selected;

    public SummaryBean(String selected) throws BeanException {
        setSelected(selected);
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) throws BeanException {
        if (!selected.equals("All") && !selected.equals("Last Month") && !selected.equals("Last Week"))
            throw new BeanException("time selection", "not a valid time period");
        this.selected = selected;
    }
}
