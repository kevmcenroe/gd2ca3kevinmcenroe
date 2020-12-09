// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.io.*;
import java.time.LocalDate;
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

    public void deleteBookingsFromFile(ComputerBooking booking)
    {
        // POSSIBLE FIX - Don't create a new fileWriter, line below was originally a bw
        // This new solution still doesn't do the trick. Try passing bufferedWriter / reader in?
        try(BufferedReader bookingsFile = new BufferedReader(new FileReader("bookings.txt"))) {
            //BufferedReader bufferedWriter = new BufferedReader(new FileReader("bookings.txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("bookings.txt"));

            String currentLine;
            String bookingLine = booking.getID() + "," + booking.getDateTime() + "," + booking.getReturnDateTime() + "," + booking.getComputerType() + ","  +booking.getComputerAssetTag() + "," + booking.getStudentID();

            // While there are lines to read
            while((currentLine = bookingsFile.readLine()) != null){
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(bookingLine)){
                    currentLine = "";
                }
                bufferedWriter.write(currentLine + System.getProperty("line.separator")); // Essentially adding a new blank line after the current one using "\n"

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
        String bookingDateTime = enterField("date and time in yyyy-MM-dd format");
        String bookingReturnDateTime = enterField("return date and time in yyyy-MM-dd format");
        String bookingComputerType = enterField("computer type");
        String bookingAssetTag = enterField("asset tag");
        String bookingStudentID = enterField("student id");

        ComputerBooking newBooking = new ComputerBooking(bookingID, bookingDateTime, bookingReturnDateTime, bookingComputerType, bookingAssetTag, bookingStudentID);
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
            String replacementInput = keyboard.nextLine();

            editMenu = ComputerBookingEditMenu.values()[fieldSelected];
            switch (editMenu) {
                case ID:

                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s ID from " + bookingToEdit.getID() + " to " + replacementInput + Colours.RESET);
                    bookingToEdit.bookingID = replacementInput;
                    break;
                case DATE_AND_TIME:
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s DATE AND TIME from " + bookingToEdit.getDateTime() + " to " + replacementInput + Colours.RESET);
                    bookingToEdit.bookingDateTime = replacementInput;
                    break;
                case RETURN_DATE_AND_TIME:
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s RETURN DATE AND TIME from " + bookingToEdit.getReturnDateTime() + " to " + replacementInput + Colours.RESET);
                    bookingToEdit.returnDateTime = replacementInput;
                    break;
                case COMPUTER_TYPE:
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s COMPUTER TYPE from " + bookingToEdit.getComputerType() + " to " + replacementInput + Colours.RESET);
                    bookingToEdit.computerType = replacementInput;
                    break;
                case COMPUTER_ASSET_TAG:
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s COMPUTER ASSET TAG from " + bookingToEdit.getComputerAssetTag() + " to " + replacementInput + Colours.RESET);
                    bookingToEdit.computerAssetTag = replacementInput;
                    break;
                case STUDENT_ID:
                    System.out.println(Colours.GREEN + "Edited " + bookingToEdit.getID() + "'s STUDENT ID from " + bookingToEdit.getStudentID() + " to " + replacementInput + Colours.RESET);
                    bookingToEdit.studentID = replacementInput;
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

                if(checkBookingIDValidity(inputID)){

                    return inputID;
                }
                else
                {
                    throw new IOException("Invalid ID");
                }
            }
            catch(IOException ioe)
            {
                System.out.println(Colours.RED + "ID already in use. Please enter a unique ID" + Colours.RESET);
            }
        }
        return Colours.RED + "Invalid ID" + Colours.RESET;
    }

    private int loopUntilValidIntEntry(String intField){
        boolean loop = true;
        while(loop)
        {
            try{
                if(intField.equals("id")) {
                    //String unhyphenated = intField.replaceAll("[-,]", "");
                    int id = Integer.parseInt(enterField(intField));
                    return id;
                }
            }
            catch(NumberFormatException nfe)
            {
                System.out.println(Colours.RED + "Please enter an integer for this variable" + Colours.RESET);
            }
        }
        return -1;
    }

    public void deleteBooking() {
        System.out.println(Colours.GREEN + "Deleting booking..." + Colours.RESET);

        if(this.bookings != null)
        {
            String nameToDelete = enterField("name to delete");
            ComputerBooking bookingToDelete = findBooking(nameToDelete);
            if(bookingToDelete != null)
            {
                bookings.remove(bookingToDelete);
                deleteBookingsFromFile(bookingToDelete);
                System.out.println(Colours.GREEN + "Deleted " + bookingToDelete + Colours.RESET);
            }
            else
            {
                System.out.println(Colours.RED + "That booking does not exist" + Colours.RESET);
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
        else
        {
            System.out.println(Colours.RED + "That booking does not exist" + Colours.RESET);
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
        ArrayList<ComputerBooking> studentBookings = new ArrayList<ComputerBooking>();

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
            Collections.sort(studentBookings, dateTimeCompare);     // Use the comparator to sort the times

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
}