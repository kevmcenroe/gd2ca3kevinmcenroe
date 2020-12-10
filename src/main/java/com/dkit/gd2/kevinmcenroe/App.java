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
        App playerApp = new App();
        playerApp.start();
    }

    private void start()
    {
        System.out.println(Colours.GREEN + "Welcome to the App" + Colours.RESET);
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
                // main menu enum, 0 represents one of the options
                MainMenu menuOption = MainMenu.values()[option];
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
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
        System.out.println("Thanks for using the app");
    }

    private void doStudentMainMenuLoop(StudentDB studentDB) {
        boolean loop = true;
        while(loop)
        {
            printStudentMainMenu();

            try
            {
                int option = keyboard.nextInt();
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
                keyboard.nextLine(); //Flush the buffer
                // Main Menu enum, 0 represents one of the options
                StudentMainMenu menuOption = StudentMainMenu.values()[option];
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
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
        System.out.println(Colours.GREEN + "Thanks for using the app" + Colours.RESET);
    }

    private void printMainMenu()
    {
        System.out.println("\nMenu Options:");
        for(int i=0; i < MainMenu.values().length; i++)
        {
            String menuOption = MainMenu.values()[i].toString().replaceAll("_", " ");
            System.out.println("\t" + Colours.BLUE + i + ". " + menuOption + Colours.RESET);
        }
        System.out.println("Enter the corresponding number to select an option");
    }

    public static void printStudentMainMenu()
    {
        System.out.println("\nMenu Options:");
        for(int i=0; i < StudentMainMenu.values().length; i++)
        {
            String menuOption = StudentMainMenu.values()[i].toString().replaceAll("_", " ");
            System.out.println("\t" + Colours.BLUE + i + ". " + menuOption + Colours.RESET);
        }
        System.out.println("Enter the corresponding number to select an option");
    }

    private void doComputerBookingMainMenuLoop(ComputerBookingDB bookingDB) {
        boolean loop = true;
        while(loop)
        {
            printComputerBookingMainMenu();

            try
            {
                int option = keyboard.nextInt();
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
                keyboard.nextLine(); //Flush the buffer
                ComputerBookingMainMenu menuOption = ComputerBookingMainMenu.values()[option];
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
                    case PRINT_AVERAGE_BOOKING_LENGTH:
                        bookingDB.printAverageBookingLength();
                        break;
                    case RETURN_BOOKED_COMPUTER:
                        bookingDB.returnBooking();
                        break;
                    case PRINT_BOOKING_STATISTICS:
                        doStatisticsMainMenuLoop(bookingDB);
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
        System.out.println(Colours.GREEN + "Thanks for using the app" + Colours.RESET);
    }

    private void printComputerBookingMainMenu()
    {
        System.out.println("\nMenu Options:");
        for(int i=0; i < ComputerBookingMainMenu.values().length; i++)
        {
            String menuOption = ComputerBookingMainMenu.values()[i].toString().replaceAll("_", " ");
            System.out.println("\t" + Colours.BLUE + i + ". " + menuOption + Colours.RESET);
        }
        System.out.println("Enter the corresponding number to select an option");
    }

    private void doStatisticsMainMenuLoop(ComputerBookingDB bookingDB) {
        boolean loop = true;
        while(loop)
        {
            printStatisticsMainMenu();

            try
            {
                int option = keyboard.nextInt();
                keyboard.nextLine(); //Flush the buffer
                BookingStatsMainMenu menuOption = BookingStatsMainMenu.values()[option];
                switch (menuOption)
                {
                    case QUIT_STATISTICS_MENU:
                        doComputerBookingMainMenuLoop(bookingDB);
                        break;
                    case DESKTOPS_BOOKED:
                        bookingDB.printComputerTypeBooked("Desktop");
                        break;
                    case LAPTOPS_BOOKED:
                        bookingDB.printComputerTypeBooked("Laptop");
                        break;
                    case RASPBERRY_PIs_BOOKED:
                        bookingDB.printComputerTypeBooked("Raspberry Pi");
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

    private void printStatisticsMainMenu()
    {
        System.out.println("\nMenu Options:");
        for(int i=0; i < BookingStatsMainMenu.values().length; i++)
        {
            String menuOption = BookingStatsMainMenu.values()[i].toString().replaceAll("_", " ");
            System.out.println("\t" + Colours.BLUE + i + ". " + menuOption + Colours.RESET);
        }
        System.out.println("Enter the corresponding number to select an option");
    }
}
