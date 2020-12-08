// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

public class Laptop extends Computer{
    double screenSizeInches;
    int batteryLifeHours;

    public Laptop(String manufacturer, String processor, int ramSizeGB, int diskSizeGB, int weight, String assetTag,
                  String purchaseDate, double screenSizeInches, int batteryLifeHours) {
        super(manufacturer, processor, ramSizeGB, diskSizeGB, weight, assetTag, purchaseDate);
        this.screenSizeInches = screenSizeInches;
        this.batteryLifeHours = batteryLifeHours;
    }
}
