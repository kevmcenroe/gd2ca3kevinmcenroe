// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Manages many students
public class StudentDB {
    private ArrayList<Student> students;
    private static Scanner keyboard = new Scanner(System.in);

    public StudentDB() {
        this.students = new ArrayList<>();
    }

    protected void loadStudentsFromFile(){
        try(Scanner studentsFile = new Scanner(new BufferedReader(new FileReader("students.txt"))))
        {
            String input;
            while(studentsFile.hasNextLine())
            {
                input = studentsFile.nextLine();
                String[] data = input.split(",");
                String studentName = data[0];
                String studentID = data[1];
                String studentEmail = data[2];
                String studentTelephone = data[3];
                String studentComputersOnLoad = data[4];

                Student readInStudent = new Student(studentName, studentID, studentEmail, studentTelephone, studentComputersOnLoad);
                students.add(readInStudent);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println("Could not load students. If this is the first time running the program, this could be ok");
        }
     }

     public void saveStudentsToFile()
     {
        try(BufferedWriter studentsFile = new BufferedWriter(new FileWriter("students.txt"))) {
            for(Student student : students)
            {
                studentsFile.write(student.getName() + "," + student.getID() + "," + student.getEmail() + "," + student.getTelephone() + "," +student.getComputersOnLoan() +"\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.RED + "Could not save the students" +Colours.RESET);
        }
    }

     public void addStudent() {
        System.out.println(Colours.GREEN + "Adding student..." + Colours.RESET);
        String name = enterField("name");
        String id = loopUntilValidIDEntry("id");
        String email = enterField("email");
        String telephone = Integer.toString(loopUntilValidIntEntry("telephone"));
        String computersOnLoan = enterField("computers on loan, separating multiple bookings with an ampersand (&)");

        Student newStudent = new Student(name, id, email, telephone, computersOnLoan);
        students.add(newStudent);
        saveStudentsToFile();

        System.out.println(Colours.GREEN + "Added student: " + newStudent + Colours.RESET);
    }

    public void editStudent(){
        StudentEditMenu editMenu;
        System.out.println("Please enter the ID of the student you would like to edit");
        String id = enterField("id");

        Student studentToEdit = findStudent(id);
        if(studentToEdit != null) {
            System.out.println(Colours.GREEN + studentToEdit + Colours.RESET);
            System.out.println("Select the field you would like to edit");
            System.out.println(Colours.BLUE +
                    "0. NAME \n"
                    + "1. ID \n"
                    + "2. EMAIL \n"
                    + "3. TELEPHONE \n"
                    + "4. COMPUTERS ON LOAN" + Colours.RESET);

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

            editMenu = StudentEditMenu.values()[fieldSelected];
            switch (editMenu) {
                case NAME:

                    System.out.println(Colours.GREEN + "Edited " + studentToEdit.getName() + "'s NAME from " + studentToEdit.getName() + " to " + replacementInput + Colours.RESET);
                    studentToEdit.name = replacementInput;
                    break;
                case ID:
                    System.out.println(Colours.GREEN + "Edited " + studentToEdit.getName() + "'s ID from " + studentToEdit.getID() + " to " + replacementInput + Colours.RESET);
                    studentToEdit.id = replacementInput;
                    break;
                case EMAIL:
                    System.out.println(Colours.GREEN + "Edited " + studentToEdit.getName() + "'s EMAIL from " + studentToEdit.getEmail() + " to " + replacementInput + Colours.RESET);
                    studentToEdit.email = replacementInput;
                    break;
                case TELEPHONE:
                    System.out.println(Colours.GREEN + "Edited " + studentToEdit.getName() + "'s TELEPHONE from " + studentToEdit.getTelephone() + " to " + replacementInput + Colours.RESET);
                    studentToEdit.telephone = replacementInput;
                    break;
                case COMPUTERS_ON_LOAN:
                    System.out.println(Colours.GREEN + "Edited " + studentToEdit.getName() + "'s COMPUTERS ON LOAN from " + studentToEdit.getComputersOnLoan() + " to " + replacementInput + Colours.RESET);
                    studentToEdit.computersOnLoan = replacementInput;
                    break;
            }

            saveStudentsToFile();
        }
        else
        {
            System.out.println(Colours.RED + "This student id does not exist" + Colours.RESET);
            //editStudent();  // Recursion
        }
    }

    // Checks that the ID is unique, in turn preventing duplicate students
    private boolean checkStudentIDValidity(String proposedID){
        for(Student student : students){
            if(student.getID().equals(proposedID))
                return false;    // A match has been found. This studentID is already in use
        }

        return true;
    }


    private String enterField(String field) {
        String input;
        System.out.print("Please enter student " + field + " :>");

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

                if(checkStudentIDValidity(inputID)){

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
                if(intField.equals("telephone")) {
                    //String unhyphenated = intField.replaceAll("[-,]", "");
                    int telephone = Integer.parseInt(enterField(intField));
                    return telephone;
                }
            }
            catch(NumberFormatException nfe)
            {
                System.out.println(Colours.RED + "Please enter an integer for this variable" + Colours.RESET);
            }
        }
        return -1;
    }

    public void deleteStudent() {
        System.out.println(Colours.GREEN + "Deleting student..." + Colours.RESET);

        if(this.students != null)
        {
            String idToDelete = enterField("id to delete");
            Student studentToDelete = findStudent(idToDelete);
            if(studentToDelete != null)
            {
                students.remove(studentToDelete);
                saveStudentsToFile();
                System.out.println(Colours.GREEN + "Deleted " + studentToDelete + Colours.RESET);
            }
            else
            {
                System.out.println(Colours.RED + "That student does not exist" + Colours.RESET);
            }
        }
    }

    private Student findStudent(String searchID) {
        for(Student student : students){
            if(student.getID().equals(searchID))
            {
                return student;
            }
        }
        return null;
    }

    public void printStudent() {
        System.out.println(Colours.GREEN + "Printing student..." + Colours.RESET);
        String idToPrint = enterField("id to print");
        Student studentToPrint = findStudent(idToPrint);
        if(studentToPrint != null)
        {
            System.out.println(Colours.GREEN + studentToPrint + Colours.RESET);
        }
        else
        {
            System.out.println(Colours.RED + "That student does not exist" + Colours.RESET);
        }
    }

}
