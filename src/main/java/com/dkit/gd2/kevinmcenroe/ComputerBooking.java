// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.util.Date;

public class ComputerBooking {
    String bookingID;
    String bookingDateTime;
    String returnDateTime;
    String computerType;
    String computerAssetTag;
    String studentID;

    public ComputerBooking(String bookingID, String bookingDateAndTime, String returnDateAndTime, String computerType, String computerAssetTag, String studentID) {
        this.bookingID = bookingID;
        this.bookingDateTime = bookingDateAndTime;
        this.returnDateTime = returnDateAndTime;
        this.computerType = computerType;
        this.computerAssetTag = computerAssetTag;
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "ComputerBooking{" +
                "bookingID='" + bookingID + '\'' +
                ", bookingDateTime='" + bookingDateTime + '\'' +
                ", returnDateTime='" + returnDateTime + '\'' +
                ", computerType='" + computerType + '\'' +
                ", computerAssetTag='" + computerAssetTag + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }

    public String getID() {
        return bookingID;
    }

    public String getDateTime() {
        return bookingDateTime;
    }

    public String getReturnDateTime() {
        return returnDateTime;
    }

    public String getComputerType() {
        return computerType;
    }

    public String getComputerAssetTag() {
        return computerAssetTag;
    }

    public String getStudentID() {
        return studentID;
    }
}