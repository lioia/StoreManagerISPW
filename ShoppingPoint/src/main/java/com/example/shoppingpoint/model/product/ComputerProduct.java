package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ComputerType;
import com.example.shoppingpoint.utils.StatusType;

public class ComputerProduct extends Product {
    private ComputerType computerType;
    private Integer ram, ssd, batterySize;
    private String cpu, gpu, brand;
    private Float displaySize;

    public ComputerProduct(String name, Number price, Number discountedPrice, Integer quantity, StatusType status, ComputerType computerType, Integer ram, Integer ssd, Integer batterySize, String cpu, String gpu, String brand, Float displaySize) {
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setComputerType(computerType);
        setRam(ram);
        setSsd(ssd);
        setBatterySize(batterySize);
        setCpu(cpu);
        setGpu(gpu);
        setBrand(brand);
        setDisplaySize(displaySize);
    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getSsd() {
        return ssd;
    }

    public void setSsd(Integer ssd) {
        this.ssd = ssd;
    }

    public Integer getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(Integer batterySize) {
        this.batterySize = batterySize;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Float getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(Float displaySize) {
        this.displaySize = displaySize;
    }
}
