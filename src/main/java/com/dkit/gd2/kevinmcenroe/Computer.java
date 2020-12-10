// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

public class Computer {
    String manufacturer;
    String processor;
    int ramSizeGB;
    int diskSizeGB;
    int weight;
    String assetTag;
    String purchaseDate;

    public Computer(String manufacturer, String processor, int ramSizeGB, int diskSizeGB, int weight, String assetTag, String purchaseDate) {
        this.manufacturer = manufacturer;
        this.processor = processor;
        this.ramSizeGB = ramSizeGB;
        this.diskSizeGB = diskSizeGB;
        this.weight = weight;
        this.assetTag = assetTag;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "manufacturer='" + manufacturer + '\'' +
                ", processor='" + processor + '\'' +
                ", ramSizeGB=" + ramSizeGB +
                ", diskSizeGB=" + diskSizeGB +
                ", weight=" + weight +
                ", assetTag='" + assetTag + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getProcessor() {
        return processor;
    }

    public int getRamSizeGB() {
        return ramSizeGB;
    }

    public int getDiskSizeGB() {
        return diskSizeGB;
    }

    public int getWeight() {
        return weight;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}
