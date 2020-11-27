package com.dkit.gd2.kevinmcenroe;

public class Desktop extends Computer {
    boolean hasMonitor;

    public Desktop(String manufacturer, String processor, int ramSizeGB, int diskSizeGB, int weight, String assetTag,
                   String purchaseDate, boolean hasMonitor) {
        super(manufacturer, processor, ramSizeGB, diskSizeGB, weight, assetTag, purchaseDate);
        this.hasMonitor = hasMonitor;
    }
}
