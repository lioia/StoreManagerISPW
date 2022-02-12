package com.shoppingpoint.model.product;

import com.shoppingpoint.utils.ProductType;

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
        setComputerRam(ram);
        setComputerSsd(ssd);
        setComputerBatterySize(batterySize);
        setComputerCpu(cpu);
        setComputerGpu(gpu);
        setComputerBrand(brand);
        setComputerDisplaySize(displaySize);
        setType(ProductType.COMPUTER);
    }

    public String getComputerType() {
        return computerType;
    }

    public void setComputerType(String computerType) {
        this.computerType = computerType;
    }

    public Integer getComputerRam() {
        return ram;
    }

    public void setComputerRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getComputerSsd() {
        return ssd;
    }

    public void setComputerSsd(Integer ssd) {
        this.ssd = ssd;
    }

    public Integer getComputerBatterySize() {
        return batterySize;
    }

    public void setComputerBatterySize(Integer batterySize) {
        this.batterySize = batterySize;
    }

    public String getComputerCpu() {
        return cpu;
    }

    public void setComputerCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getComputerGpu() {
        return gpu;
    }

    public void setComputerGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getComputerBrand() {
        return brand;
    }

    public void setComputerBrand(String brand) {
        this.brand = brand;
    }

    public Float getComputerDisplaySize() {
        return displaySize;
    }

    public void setComputerDisplaySize(Float displaySize) {
        this.displaySize = displaySize;
    }
}
