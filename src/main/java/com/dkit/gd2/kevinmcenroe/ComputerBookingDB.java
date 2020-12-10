// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
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
                String bookingID = data[0];
                String bookingDateTime = data[1];
                String bookingReturnDateTime = data[2];
                String bookingComputerType = data[3];
                String bookingComputerTag = data[4];
                String bookingStudentID = data[5];

                ComputerBooking readInBooking = new ComputerBooking(bookingID, bookingDateTime, bookingReturnDateTime, bookingComputerType, bookingComputerTag, bookingStudentID);
                bookings.add(readInBooking);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println("Could not load bookings. If this is the first time running the program, this could be ok");
        }
    }

    public void saveBookingsToFile()
    {
        try(BufferedWriter bookingsFile = new BufferedWriter(new FileWriter("bookings.txt"))) {
            for(ComputerBooking booking : bookings)
            {
                bookingsFile.write(booking.getID() + "," + booking.getDateTime() + "," + booking.getReturnDateTime() + "," + booking.getComputerType() + "," +booking.getComputerAssetTag() + "," + booking.getStudentID() + "\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.RED + "Could not save the bookings" +Colours.RESET);
        }
    }

    public void addBooking() {
        System.out.println(Colours.GREEN + "Adding booking..." + Colours.RESET);
        String bookingID = loopUntilValidIDEntry("id");
        String bookingDateTime = loopUntilValidDateEntry(false);
        String bookingComputerType = loopUntilValidComputerTypeEntry("computer type (Desktop, Laptop or Raspberry Pi)");
        String bookingAssetTag = enterField("asset tag");
        String bookingStudentID = enterField("student id");

        ComputerBooking newBooking = new ComputerBooking(bookingID, bookingDateTime, "", bookingComputerType, bookingAssetTag, bookingStudentID);
        bookings.add(newBooking);
        saveBookingsToFile();
    }

    public void editBooking(){
        ComputerBookingEditMenu editMenu;
        System.out.println("Please enter the id of the booking you would like to edit");
        String id = enterField("id");

        ComputerBooking bookingToEdit = findBooking(id);
        if(bookingToEdit != null) {
            System.out.println(Colours.GREEN + bookingToEdit + Colours.RESET);
            System.out.println("Select the field you would like to edit");
            System.out.println(Colours.BLUE +
                    "0. ID \n"
                    + "1. DATE AND TIME \n"
                    + "2. RETURN DATE AND TIME \n"
                    + "3. COMPUTER TYPE \n"
                    + "4. COMPUTER ASSET TAG \n"
                    + "5. STUDENT ID" + Colours.RESET);

            String menuInput = keyboard.nextLine();
            int fieldSelected = -1;
            try {
                if (menuInput.isEmpty() || menuInput.length() > 1) {
                    throw new IllegalArgumentException();
                } else {
                    fieldSelected = Integer.parseInt(menuInput);
                }
            } catch (IllegalArgumentException iae) {
                System.out.println("Please enter a valid option");
            }

            System.out.println("Please enter a replacement value");

            editMenu = ComputerBookingEditMenu.values()[fieldSelected];
            switch (editMenu) {
                case ID:
                    String bookingID = loopUntilValidIDEntry("id");
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s ID from " + bookingToEdit.getID() + " to " + bookingID + Colours.RESET);
                    bookingToEdit.bookingID = bookingID;
                    break;
                case DATE_AND_TIME:
                    String bookingDateTime = loopUntilValidDateEntry(false);
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s DATE from " + bookingToEdit.getDateTime() + " to " + bookingDateTime + Colours.RESET);
                    bookingToEdit.bookingDateTime = bookingDateTime;
                    break;
                case RETURN_DATE_AND_TIME:
                    String returnBookingDateTime = loopUntilValidDateEntry(true);
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s RETURN DATE from " + bookingToEdit.getReturnDateTime() + " to " + returnBookingDateTime + Colours.RESET);
                    bookingToEdit.returnDateTime = returnBookingDateTime;
                    break;
                case COMPUTER_TYPE:
                    String bookingComputerType = loopUntilValidComputerTypeEntry("computer type (Desktop, Laptop or Raspberry Pi)");
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s COMPUTER TYPE from " + bookingToEdit.getComputerType() + " to " + bookingComputerType + Colours.RESET);
                    bookingToEdit.computerType = bookingComputerType;
                    break;
                case COMPUTER_ASSET_TAG:
                    String bookingAssetTag = enterField("asset tag");
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s COMPUTER ASSET TAG from " + bookingToEdit.getComputerAssetTag() + " to " + bookingAssetTag + Colours.RESET);
                    bookingToEdit.computerAssetTag = bookingAssetTag;
                    break;
                case STUDENT_ID:
                    String bookingStudentID = enterField("student id");
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s STUDENT ID from " + bookingToEdit.getStudentID() + " to " + bookingStudentID + Colours.RESET);
                    bookingToEdit.studentID = bookingStudentID;
                    break;
            }

            saveBookingsToFile();
        }
        else
        {
            System.out.println(Colours.RED + "This booking ID does not exist" + Colours.RESET);
        }
    }

    // Checks that the ID is unique, in turn preventing duplicate bookings
    private boolean checkBookingIDValidity(String proposedID){
        for(ComputerBooking booking : bookings){
            if(booking.getID().equals(proposedID))
                return false;
        }

        return true;
    }

    public void returnBooking()
    {
        System.out.println("Please enter the id of the booking you would like to return");
        String id = enterField("id");
        ComputerBooking bookingToReturn = findBooking(id);
        if(bookingToReturn != null)
        {
            String bookingReturnDateTime = loopUntilValidDateEntry(true);
            System.out.println("TEST TEST TEST bookingsReturnDate = " + bookingReturnDateTime);
            bookingToReturn.returnDateTime = bookingReturnDateTime;
            saveBookingsToFile();
            System.out.println(Colours.GREEN + "Booking of ID " + id + " was returned on " + bookingReturnDateTime + Colours.RESET);
        }
    }

    private String enterField(String field) {
        String input;
        System.out.print("Please enter booking " + field + " :>");
        // Don't need a try here because input will always be a string, therefore incorrect input types won't happen

        input = keyboard.nextLine();
        return input;
    }

    private String loopUntilValidIDEntry(String idField){
        boolean loop = true;

        while(loop)
        {
            try{
                String inputID = enterField(idField);

                if(checkBookingIDValidity(inputID))
                    return inputID;
                else
                    throw new IOException("Invalid ID");
            }
            catch(IOException ioe)
            {
                System.out.println(Colours.RED + "ID already in use. Please enter a unique ID" + Colours.RESET);
            }
        }
        return Colours.RED + "Invalid ID" + Colours.RESET;
    }

    private String loopUntilValidComputerTypeEntry(String typeField){
        boolean loop = true;

        while(loop)
        {
            try{
                String inputID = enterField("computer type (Desktop, Laptop or Raspberry Pi)");

                if(inputID.equals("Desktop") || inputID.equals("Laptop") || inputID.equals("Raspberry Pi")) {
                    return inputID;
                }
                else
                    throw new IOException("Invalid computer type");
            }
            catch(IOException ioe)
            {
                System.out.println(Colours.RED + "Invalid computer type. Please enter one of the following options: \nDesktop \nLaptop \nRaspberry Pi" + Colours.RESET);
            }
        }
        return Colours.RED + "Invalid ID" + Colours.RESET;
    }

    private String loopUntilValidDateEntry(boolean isReturnDate){
        boolean loop = true;

        while(loop)
        {
            try{
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String inputDate = "";
                if(isReturnDate)
                    inputDate = enterField("return date in yyyy-MM-dd format");
                else{
                    inputDate = enterField("date in yyyy-MM-dd format");
                }
                dateFormat.parse(inputDate);
                return inputDate;
            }
            catch(ParseException pe)
            {
                System.out.println(Colours.RED + "Invalid date. Please enter a date in format yyyy-MM-dd" + Colours.RESET);
            }
        }
        return Colours.RED + "Invalid date" + Colours.RESET;
    }

    public void deleteBooking() {
        System.out.println(Colours.GREEN + "Deleting booking..." + Colours.RESET);

        if(this.bookings != null)
        {
            String nameToDelete = enterField("id to delete");
            ComputerBooking bookingToDelete = findBooking(nameToDelete);
            if(bookingToDelete != null)
            {
                bookings.remove(bookingToDelete);
                saveBookingsToFile();
                System.out.println(Colours.GREEN + "Deleted " + bookingToDelete + Colours.RESET);
            }
        }
    }

    private ComputerBooking findBooking(String searchID) {
        for(ComputerBooking booking : bookings){
            if(booking.getID().equals(searchID))
            {
                return booking;
            }
        }
        System.out.println(Colours.RED + "A booking of ID " + searchID + " does not exist" + Colours.RESET);
        return null;
    }

    public void printBooking() {
        System.out.println(Colours.GREEN + "Printing booking..." + Colours.RESET);
        String nameToPrint = enterField("id to print");
        ComputerBooking bookingToPrint = findBooking(nameToPrint);
        if(bookingToPrint != null)
        {
            System.out.println(Colours.GREEN + bookingToPrint + Colours.RESET);
        }
    }

    public void printAllBookings() {
        System.out.println(Colours.GREEN + "Printing all bookings..." + Colours.RESET);

        if(bookings.size() > 0)
            for(ComputerBooking booking : bookings){
                System.out.println(Colours.GREEN + booking + Colours.RESET);
            }
        else
            System.out.println(Colours.RED + "Cannot print all bookings as no bookings exist. Please create bookings" + Colours.RESET);
    }

    public void printBookingsForStudent(){
        String studentID = loopUntilValidIDEntry("student ID");
        int matchesFound = 0;
        ArrayList<ComputerBooking> studentBookings = new ArrayList<>();

        for(ComputerBooking booking : bookings)
        {
            if(booking.getStudentID().equals(studentID)) {

                LocalDate dateTime = LocalDate.parse(booking.getDateTime());
                studentBookings.add(booking);
                matchesFound++;
            }
        }
        if(matchesFound != 0)
        {
            DateTimeComparison dateTimeCompare = new DateTimeComparison();
            Collections.sort(studentBookings, dateTimeCompare);     // Use the comparator to sort the times using dateTimeCompare

            for(ComputerBooking sortedBooking : studentBookings)
            {
                System.out.println(Colours.GREEN + sortedBooking + Colours.RESET);
            }
        }
        else
        {
            System.out.println(Colours.RED + "No bookings found for student of ID: " + studentID + Colours.RESET);
        }
    }

    public void printAverageBookingLength()
    {
        // Create an array of equal size to bookings, i.e. for 1 length value per booking
        long[] bookingLengths = new long[bookings.size()];
        int lengthsRegistered = 0;
        for(ComputerBooking booking : bookings)
        {
            LocalDate lendDate = LocalDate.parse(booking.getDateTime());
            LocalDate returnDate = LocalDate.parse(booking.getReturnDateTime());
            long bookingLength = ChronoUnit.DAYS.between(lendDate, returnDate);
            System.out.println("Length of booking of ID " + booking.getID() + ": " + bookingLength + " days");
            bookingLengths[lengthsRegistered] = bookingLength;
            lengthsRegistered++;
        }

        long sum = 0;
        for(long length : bookingLengths){
            sum += length;
        }
        long averageLength = sum / bookings.size();

        System.out.println(Colours.GREEN + "Average booking length is " + averageLength + " days" + Colours.RESET);
    }

    public void printComputerTypeBooked(String type)
    {
        int booked = 0;
        for(ComputerBooking booking : bookings)
        {
            if(booking.getComputerType().equals(type))
            {
                booked++;
            }
        }
        System.out.println(Colours.GREEN + booked + " " + type + " computers have been booked");
    }
}