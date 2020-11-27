package com.dkit.gd2.kevinmcenroe;

public class ComputerBooking {
    int bookingID;
    String bookingDateAndTime;
    String returnDateAndTime;
    String computerType;
    String computerAssetTag;
    String studentID;

    public ComputerBooking(int bookingID, String bookingDateAndTime, String returnDateAndTime, String computerType, String computerAssetTag, String studentID) {
        this.bookingID = bookingID;
        this.bookingDateAndTime = bookingDateAndTime;
        this.returnDateAndTime = returnDateAndTime;
        this.computerType = computerType;
        this.computerAssetTag = computerAssetTag;
        this.studentID = studentID;
    }
}
