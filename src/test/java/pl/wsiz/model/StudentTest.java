package pl.wsiz.model;

import org.junit.jupiter.api.Test;
import pl.wsiz.model.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate birthDate = LocalDate.of(2001, 9, 30);
        Student student = new Student("Kasia", "Wiśniewska", "kasia@student.com", "pass123", birthDate, 10234);

        assertEquals("Kasia", student.getFirstName());
        assertEquals("Wiśniewska", student.getLastName());
        assertEquals("kasia@student.com", student.getEmail());
        assertEquals("pass123", student.getPassword());
        assertEquals(birthDate, student.getDateOfBirth());
        assertEquals(10234, student.getAlbumNumber());
    }

    @Test
    void testSetStudentId() {
        Student student = new Student();
        student.setAlbumNumber(5555);

        assertEquals(5555, student.getAlbumNumber());
    }

    @Test
    void testToStringContainsNameAndId() {
        Student student = new Student("Adam", "Nowak", "adam@student.com", "qwerty", LocalDate.now(), 9988);
        String result = student.toString();

        assertTrue(result.contains("Adam"));
        assertTrue(result.contains("Nowak"));
        assertTrue(result.contains("9988"));
    }
}
