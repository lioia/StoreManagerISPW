package com.example.shoppingpoint.model.product;

import com.example.shoppingpoint.utils.ProductType;

public class ComputerProduct extends Product {
    private String computerType;
    private Integer ram;
    private Integer ssd;
    private Integer batterySize;
    private String cpu;
    private String gpu;
    private String brand;
    private Float displaySize;

    public ComputerProduct(Integer id, String name, Float price, Float discountedPrice, Integer quantity, String status, String storeName, String computerType, Integer ram, Integer ssd, Integer batterySize, String cpu, String gpu, String brand, Float displaySize) {
        setId(id);
        setName(name);
        setPrice(price);
        setDiscountedPrice(discountedPrice);
        setQuantity(quantity);
        setStatus(status);
        setStoreName(storeName);
        setComputerType(computerType);
        setRam(ram);
        setSsd(ssd);
        setBatterySize(batterySize);
        setCpu(cpu);
        setGpu(gpu);
        setBrand(brand);
        setDisplaySize(displaySize);
        setType(ProductType.COMPUTER);
    }

    public String getComputerType() {
        return computerType;
    }

    public void setComputerType(String computerType) {
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
