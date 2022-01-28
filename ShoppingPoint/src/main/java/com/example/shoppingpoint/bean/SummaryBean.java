package com.example.shoppingpoint.bean;

public class SummaryBean {
    private String selected;

    public SummaryBean(String selected) throws Exception {
        setSelected(selected);
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) throws Exception {
        if (!selected.equals("All") && !selected.equals("Last Month") && !selected.equals("Last Week"))
            throw new Exception("Invalid data");
        this.selected = selected;
    }
}
