// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ComputerBookingDB {
    private ArrayList<ComputerBooking> bookings;
    private static Scanner keyboard = new Scanner(System.in);

    public ComputerBookingDB() {
        this.bookings = new ArrayList<>();
    }

    protected void loadBookingsFromFile(){
        try(Scanner bookingsFile = new Scanner(new BufferedReader(new FileReader("bookings.txt"))))
        {
            String input;
            while(bookingsFile.hasNextLine())
            {
                input = bookingsFile.nextLine();
                String[] data = input.split(",");
                String infoA = data[0];
                int infoB = Integer.parseInt(data[1]);
                int infoC = Integer.parseInt(data[2]);
                String infoD = data[3];

                // Rename data variables above
                // Come back to this and clean up:
                //Student readInStudent = new Student(name);
                //this.students.add(readInStudent);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println("Could not load bookings. If this is the first time running the program, this could be" +
                    " ok");
        }
    }
}
