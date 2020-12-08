// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.util.Date;

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

    /*
    int currentBooking = 0;
    private void AddStudent(Student newStudent){
        int newBookingID = currentBooking++;
        Date dateAndTime = java.util.Calendar.getInstance().getTime();;
        //ComputerBooking newBooking = new ComputerBooking(newBookingID, dateAndTime);
    }

    private void DeleteStudent(){

    }

    private void EditStudent(){

    }

     */
}