package com.dkit.gd2.kevinmcenroe;

import java.time.LocalDate;
import java.util.Comparator;

// Use of Comparator. See ComputerBooking class for use of Comparable
public class DateTimeComparison implements Comparator<ComputerBooking> {

    @Override
    public int compare(ComputerBooking bookingOne, ComputerBooking bookingTwo) {

        LocalDate bookingOneDateTime = LocalDate.parse(bookingOne.getDateTime());
        LocalDate bookingTwoDateTime = LocalDate.parse(bookingTwo.getDateTime());

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
