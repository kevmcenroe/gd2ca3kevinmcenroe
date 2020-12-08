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
}
