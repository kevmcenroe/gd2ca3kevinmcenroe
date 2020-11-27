package com.dkit.gd2.kevinmcenroe;

public class RaspberryPi extends Computer {
    int gpioPins;
    int sdStorageCapacity;

    public RaspberryPi(String manufacturer, String processor, int ramSizeGB, int diskSizeGB, int weight, String assetTag, String purchaseDate) {
        super(manufacturer, processor, ramSizeGB, diskSizeGB, weight, assetTag, purchaseDate);
        this.gpioPins = gpioPins;
        this.sdStorageCapacity = sdStorageCapacity;
    }
}
