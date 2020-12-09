// Kevin McEnroe D00242092
package com.dkit.gd2.kevinmcenroe;

import java.util.Arrays;

public class Student {
    String name;
    String id;
    String email;
    String telephone;
    String computersOnLoan;

    public Student(String name, String id, String email, String telephone, String computersOnLoan) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telephone = telephone;
        this.computersOnLoan = computersOnLoan;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getComputersOnLoan() {
        return computersOnLoan;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", computersOnLoan=" +computersOnLoan +
                '}';
    }

}
