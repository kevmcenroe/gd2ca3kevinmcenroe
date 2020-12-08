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
                studentsFile.write(student.getName() + "," + student.getId() + "," + student.getEmail() + "," + student.getTelephone() + "," +student.getComputersOnLoan() +"\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.RED + "Could not save the students" +Colours.RESET);
        }
    }

    public void deleteStudentsFromFile(Student student)
    {
        // POSSIBLE FIX - Don't create a new fileWriter, line below was originally a bw
        // This new solution still doesn't do the trick. Try passing bufferedWriter / reader in?
        try(BufferedReader studentsFile = new BufferedReader(new FileReader("students.txt"))) {
                //BufferedReader bufferedWriter = new BufferedReader(new FileReader("students.txt"));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("students.txt"));

                String currentLine;
                String studentLine = student.getName() + "," + student.getId() + "," + student.getEmail() + "," + student.getTelephone() + student.getComputersOnLoan();

                // While there are lines to read
                while((currentLine = studentsFile.readLine()) != null){
                    String trimmedLine = currentLine.trim();
                    if(trimmedLine.equals(studentLine)){
                        currentLine = "";
                    }
                    bufferedWriter.write(currentLine + System.getProperty("line.separator")); // Essentially adding a new blank line after the current one using "\n"

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
        String id = enterField("id");
        String email = enterField("email");
        String telephone = Integer.toString(loopUntilValidIntEntry("telephone"));
        String computersOnLoan = enterField("computers on loan");

        Student newStudent = new Student(name, id, email, telephone, computersOnLoan);
        students.add(newStudent);
        saveStudentsToFile();
    }

    private String enterField(String field) {
        String input;
        System.out.print("Please enter student " + field + " :>");

        // Don't need a try here because input will always be a string, therefore incorrect input types won't happen

        input = keyboard.nextLine();
        return input;

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
            String nameToDelete = enterField("name to delete");
            Student studentToDelete = findStudent(nameToDelete);
            if(studentToDelete != null)
            {
                students.remove(studentToDelete);
                deleteStudentsFromFile(studentToDelete);
                System.out.println(Colours.GREEN + "Deleted " + studentToDelete + Colours.RESET);
            }
            else
            {
                System.out.println(Colours.RED + "That student does not exist" + Colours.RESET);
            }
        }
    }

    private Student findStudent(String nameToDelete) {
        for(Student student : students){
            if(student.getName().equals(nameToDelete))
            {
                return student;
            }
        }
        return null;
    }

    public void printStudent() {
        System.out.println(Colours.GREEN + "Printing student..." + Colours.RESET);
        String nameToPrint = enterField("name to print");
        Student studentToPrint = findStudent(nameToPrint);
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
