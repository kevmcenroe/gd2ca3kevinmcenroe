// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.time.LocalDate;
import java.util.Date;

public class ComputerBooking implements Comparable {
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

    // Use of Comparable:
    @Override
    public int compareTo(Object otherBooking) {
        LocalDate bookingOneDateTime = LocalDate.parse(this.getDateTime());
        LocalDate bookingTwoDateTime = LocalDate.parse(((ComputerBooking)otherBooking).getDateTime());

        if(bookingOneDateTime.isBefore(bookingTwoDateTime))
        {
            return -1;
        }
        if(bookingOneDateTime.isAfter(bookingTwoDateTime))
        {
            return 1;
        }
        else
            return 0;

    }
}