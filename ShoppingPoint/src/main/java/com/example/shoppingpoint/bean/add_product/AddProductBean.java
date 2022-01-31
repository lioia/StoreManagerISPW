package com.example.shoppingpoint.bean.add_product;

import com.example.shoppingpoint.exception.BeanException;
import com.example.shoppingpoint.utils.ProductType;

public class AddProductBean {
    private String size;
    private String material;
    private String shoesType;
    private String author;
    private String artist;
    private String plot;
    private String genre;
    private int volumeNumber;
    private String consoleType;
    private boolean digitalOnly;
    private String computerType;
    private int ram;
    private int ssd;
    private String cpu;
    private String gpu;
    private int batterySize;
    private float displaySize;
    private String brand;
    private String energyClass;
    private String specs;

    public AddProductBean(ProductType type, String size, String material, String shoesType, String author, String artist, String plot, String genre,
                          String volumeNumber, String consoleType, boolean digitalOnly, String computerType, String ram, String ssd, String cpu,
                          String gpu, String batterySize, String displaySize, String brand, String energyClass, String specs) throws BeanException {
        switch (type) {
            case CLOTHES -> {
                setSize(size);
                setMaterial(material);
            }
            case SHOES -> {
                setSize(size);
                setMaterial(material);
                setShoesType(shoesType);
            }
            case BOOK -> {
                setAuthor(author);
                setPlot(plot);
                setGenre(genre);
            }
            case COMICS -> {
                setAuthor(author);
                setArtist(artist);
                setPlot(plot);
                setGenre(genre);
                setVolumeNumber(Integer.parseInt(volumeNumber));
            }
            case VIDEOGAME -> {
                setPlot(plot);
                setGenre(genre);
                setConsoleType(consoleType);
            }
            case GAMECONSOLE -> {
                setConsoleType(consoleType);
                setDigitalOnly(digitalOnly);
            }
            case COMPUTER -> {
                setComputerType(computerType);
                setRam(Integer.parseInt(ram));
                setSsd(Integer.parseInt(ssd));
                setCpu(cpu);
                setGpu(gpu);
                setBatterySize(Integer.parseInt(batterySize));
                setDisplaySize(Float.parseFloat(displaySize));
                setBrand(brand);
            }
            case HOMEAPPLIANCES -> {
                setEnergyClass(energyClass);
                setSpecs(specs);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getShoesType() {
        return shoesType;
    }

    public void setShoesType(String shoesType) {
        this.shoesType = shoesType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getVolumeNumber() {
        return volumeNumber;
    }

    public void setVolumeNumber(int volumeNumber) throws BeanException {
        if (volumeNumber < 0) throw new BeanException("volume number", "it has to be more than or equal to 0");
        this.volumeNumber = volumeNumber;
    }

    public String getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(String consoleType) {
        this.consoleType = consoleType;
    }

    public boolean isDigitalOnly() {
        return digitalOnly;
    }

    public void setDigitalOnly(boolean digitalOnly) {
        this.digitalOnly = digitalOnly;
    }

    public String getComputerType() {
        return computerType;
    }

    public void setComputerType(String computerType) {
        this.computerType = computerType;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) throws BeanException {
        if (ram < 1)
            throw new BeanException("RAM", "it has to be more than 1GB");
        this.ram = ram;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) throws BeanException {
        if (ssd < 64)
            throw new BeanException("SSD", "it has to be more than 64GB");
        this.ssd = ssd;
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

    public int getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(int batterySize) throws BeanException {
        if (batterySize < 1000)
            throw new BeanException("battery size", "it has to be more than 1000mAh");
        this.batterySize = batterySize;
    }

    public float getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(float displaySize) throws BeanException {
        if (displaySize < 7)
            throw new BeanException("display size", "it has to be more than 7 inches");
        this.displaySize = displaySize;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }
}
