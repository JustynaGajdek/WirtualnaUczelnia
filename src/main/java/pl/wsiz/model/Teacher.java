package pl.wsiz.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;

@JsonTypeName("teacher")
public class Teacher extends User {

    private String academicDegree;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String academicDegree) {
        super(firstName, lastName, email, password, dateOfBirth);
        this.academicDegree = academicDegree;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", academicDegree='" + academicDegree + '\'' +
                '}';
    }
}
