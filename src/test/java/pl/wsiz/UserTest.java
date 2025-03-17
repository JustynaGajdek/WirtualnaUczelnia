package pl.wsiz;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getFirstName() {
        User user1 = new User("Adam", "Nowak", "adam@onet.eu",
                "adam@34", LocalDate.of(1992, 5, 20));

        String firstName = user1.getFirstName();

        assertEquals("Adam", firstName);
    }

}