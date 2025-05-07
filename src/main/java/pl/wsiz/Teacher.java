package pl.wsiz;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;

@JsonTypeName("teacher")
public class Teacher extends User {

    private String subject;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String subject) {
        super(firstName, lastName, email, password, dateOfBirth);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", subject='" + subject + '\'' +
                '}';
    }
}
