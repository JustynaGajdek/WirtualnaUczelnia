package pl.wsiz.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;

@JsonTypeName("student")
public class Student extends User {

    private int albumNumber;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, int albumNumber) {
        super(firstName, lastName, email, password, dateOfBirth);
        this.albumNumber = albumNumber;
    }

    public int getAlbumNumber() {
        return albumNumber;
    }

    public void setAlbumNumber(int albumNumber) {
        this.albumNumber = albumNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", albumNumber=" + albumNumber +
                '}';
    }
}

