// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Scanner keyboard = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App playerApp = new App();
        playerApp.start();
    }

    private void start()
    {
        System.out.println(Colours.GREEN + "Welcome to the playerDB App" + Colours.RESET);
        StudentDB studentDB = new StudentDB();
        studentDB.loadStudentsFromFile();
        studentDB.saveStudentsToFile();

        ComputerBookingDB bookingDB = new ComputerBookingDB();
        bookingDB.loadBookingsFromFile();
        bookingDB.saveBookingsToFile();

        doMainMenuLoop(studentDB, bookingDB);
    }

    private void doMainMenuLoop(StudentDB studentDB, ComputerBookingDB bookingDB)
    {
        boolean loop = true;
        MainMenu menuOption;
        int option = -1;
        while(loop)
        {
            printMainMenu();
            try
            {
                String input = keyboard.nextLine();
                if(input.isEmpty() || input.length() > 1)
                {
                    throw new IllegalArgumentException();
                }
                else
                {
                    option = Integer.parseInt(input);
                }
                if(option < 0 || option >= MainMenu.values().length)
                {
                    throw new IllegalArgumentException();
                }
               // keyboard.nextLine(); //Flush the buffer, making it ignore Enter press
                menuOption = MainMenu.values()[option]; // main menu enum, 0 represents one of the options
                switch (menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break; // exit the loop
                    case DISPLAY_STUDENT_MENU:
                        doStudentMainMenuLoop(studentDB);
                        break;
                    case DISPLAY_BOOKING_MENU:
                        doComputerBookingMainMenuLoop(bookingDB);
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println("Please enter a valid option");
                keyboard.nextLine(); // Flush the buffer to forget the invalid entry
            }
            catch(IllegalArgumentException iae)
            {
                System.out.println("Please enter a valid option");
            }
        }
        System.out.println("Thanks for using the app");
    }

    private void doStudentMainMenuLoop(StudentDB studentDB) {
        boolean loop = true;
        StudentMainMenu menuOption;
        int option;
        while(loop)
        {
            printStudentMainMenu();

            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine(); //Flush the buffer
                menuOption = StudentMainMenu.values()[option]; // Main Menu enum, 0 represents one of the options
                switch (menuOption)
                {
                    case QUIT_STUDENT_MENU:
                        loop = false;
                        break; // exit the loop
                    case ADD_STUDENT:
                        studentDB.addStudent();
                        break;
                    case EDIT_STUDENT:
                        studentDB.editStudent();
                        break;
                    case DELETE_STUDENT:
                        studentDB.deleteStudent();
                        break;
                    case PRINT_STUDENT:
                        studentDB.printStudent();
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println("Please enter a valid option");
            }
        }
        System.out.println(Colours.GREEN + "Thanks for using the app" + Colours.RESET);
    }

    private void printMainMenu()
    {
        System.out.println("\nOptions to select:");
        for(int i=0; i < MainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + MainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select an option");
    }

    public static void printStudentMainMenu()
    {
        System.out.println("\nOptions to select:");
        for(int i=0; i < StudentMainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + StudentMainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select an option");
    }

    private void printComputerBookingMainMenu()
    {
        System.out.println("\n Menu Options:");
        for(int i=0; i < ComputerBookingMainMenu.values().length; i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + ComputerBookingMainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter the corresponding number to select an option");
    }

    private void doComputerBookingMainMenuLoop(ComputerBookingDB bookingDB) {
        boolean loop = true;
        ComputerBookingMainMenu menuOption;
        int option;
        while(loop)
        {
            printComputerBookingMainMenu();

            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine(); //Flush the buffer
                menuOption = ComputerBookingMainMenu.values()[option];
                switch (menuOption)
                {
                    case QUIT_BOOKING_MENU:
                        loop = false;
                        break; // exit the loop
                    case ADD_BOOKING:
                        bookingDB.addBooking();
                        break;
                    case EDIT_BOOKING:
                        bookingDB.editBooking();
                        break;
                    case DELETE_BOOKING:
                        bookingDB.deleteBooking();
                        break;
                    case PRINT_BOOKING:
                        bookingDB.printBooking();
                        break;
                    case PRINT_ALL_BOOKINGS:
                        bookingDB.printAllBookings();
                        break;
                    case PRINT_BOOKINGS_FOR_STUDENT:
                        bookingDB.printBookingsForStudent();
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println("Please enter a valid option");
            }
        }
        System.out.println(Colours.GREEN + "Thanks for using the app" + Colours.RESET);
    }
}
