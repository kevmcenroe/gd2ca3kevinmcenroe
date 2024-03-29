// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

public class Desktop extends Computer {
    boolean hasMonitor;

    public Desktop(String manufacturer, String processor, int ramSizeGB, int diskSizeGB, int weight, String assetTag,
                   String purchaseDate, boolean hasMonitor) {
        super(manufacturer, processor, ramSizeGB, diskSizeGB, weight, assetTag, purchaseDate);
        this.hasMonitor = hasMonitor;
    }

    @Override
    public String toString() {
        return "Desktop{" +
                "hasMonitor=" + hasMonitor +
                ", manufacturer='" + manufacturer + '\'' +
                ", processor='" + processor + '\'' +
                ", ramSizeGB=" + ramSizeGB +
                ", diskSizeGB=" + diskSizeGB +
                ", weight=" + weight +
                ", assetTag='" + assetTag + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                '}';
    }

    public boolean hasMonitor() {
        return hasMonitor;
    }
}
