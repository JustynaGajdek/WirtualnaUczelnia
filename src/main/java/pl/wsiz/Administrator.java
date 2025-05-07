package pl.wsiz;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDate;

@JsonTypeName("administrator")
public class Administrator extends User {
    public Administrator() {
    }

    public Administrator(String firstName, String lastName, String email, String password, LocalDate dateOfBirth) {
        super(firstName, lastName, email, password, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                '}';
    }
}