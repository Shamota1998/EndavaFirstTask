package com.endava.internship.s2020.hashmaptask;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class Student {

    private String name;
    private LocalDate dateOfBirth;
    private String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student that = (Student) o;
        return Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getDateOfBirth(), that.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return (31 * this.getName().hashCode() + this.getDateOfBirth().hashCode());
    }


    @Override
    public String toString() {
        return "Name : " + this.name + " Date Of Birth : " + this.dateOfBirth + " Detail : " + this.details;
    }

}