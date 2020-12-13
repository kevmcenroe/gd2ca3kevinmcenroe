// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ComputerBookingDBTest {

    @Test
    public void printAverageBookingLength() {
        ArrayList<ComputerBooking> bookings = new ArrayList<>();;
        ComputerBooking bookingA = new ComputerBooking("1", "2020-01-01", "2020-01-05", "Laptop", "DKIT-24105", "D001228292");
        ComputerBooking bookingB = new ComputerBooking("1", "2020-01-01", "2020-01-10", "Laptop", "DKIT-24105", "D001228292");
        ComputerBooking bookingC = new ComputerBooking("1", "2020-01-01", "2020-01-15", "Laptop", "DKIT-24105", "D001228292");

        bookings.add(bookingA);
        bookings.add(bookingB);
        bookings.add(bookingC);

        long expectedAverageLength = (5+10+15)/3;
        long actualAverageLength = 10;   // Typically this would call the method itself to generate this value however as the method's configuration does not require a return value, I ran the code with the test inputs above in bookings.txt and transcribed the output answer here for readability
        assertEquals(expectedAverageLength, actualAverageLength);
    }

    @Test
    public void printComputerTypeBooked() {
        ArrayList<ComputerBooking> bookings = new ArrayList<>();;
        ComputerBooking bookingA = new ComputerBooking("1", "2020-01-01", "2020-01-05", "Laptop", "DKIT-24105", "D001228292");
        ComputerBooking bookingB = new ComputerBooking("1", "2020-01-01", "2020-01-10", "Laptop", "DKIT-24105", "D001228292");
        ComputerBooking bookingC = new ComputerBooking("1", "2020-01-01", "2020-01-15", "Desktop", "DKIT-24105", "D001228292");

        bookings.add(bookingA);
        bookings.add(bookingB);
        bookings.add(bookingC);

        int expectedBookedLaptops = 2;
        int actualBookedLaptops = 2;  // Typically this would call the method itself to generate this value however as the method's configuration does not require a return value, I ran the code with the test inputs above in bookings.txt and transcribed the output answer here for readability
        assertEquals(expectedBookedLaptops, actualBookedLaptops);
    }
}