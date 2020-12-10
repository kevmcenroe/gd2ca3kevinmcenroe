// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

public class RaspberryPi extends Computer {
    int gpioPins;
    int sdStorageCapacity;

    public RaspberryPi(String manufacturer, String processor, int ramSizeGB, int diskSizeGB, int weight, String assetTag, String purchaseDate) {
        super(manufacturer, processor, ramSizeGB, diskSizeGB, weight, assetTag, purchaseDate);
        this.gpioPins = gpioPins;
        this.sdStorageCapacity = sdStorageCapacity;
    }

    @Override
    public String toString() {
        return "RaspberryPi{" +
                "gpioPins=" + gpioPins +
                ", sdStorageCapacity=" + sdStorageCapacity +
                ", manufacturer='" + manufacturer + '\'' +
                ", processor='" + processor + '\'' +
                ", ramSizeGB=" + ramSizeGB +
                ", diskSizeGB=" + diskSizeGB +
                ", weight=" + weight +
                ", assetTag='" + assetTag + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }

    public int getGpioPins() {
        return gpioPins;
    }

    public int getSdStorageCapacity() {
        return sdStorageCapacity;
    }
}
