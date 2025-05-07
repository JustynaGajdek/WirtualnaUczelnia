package pl.wsiz;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate birthDate = LocalDate.of(1990, 5, 10);
        Teacher teacher = new Teacher("Anna", "Zielińska", "anna@school.com", "nauczyciel1", birthDate, "Physics");

        assertEquals("Anna", teacher.getFirstName());
        assertEquals("Zielińska", teacher.getLastName());
        assertEquals("anna@school.com", teacher.getEmail());
        assertEquals("nauczyciel1", teacher.getPassword());
        assertEquals(birthDate, teacher.getDateOfBirth());
        assertEquals("Physics", teacher.getSubject());
    }

    @Test
    void testSetSubject() {
        Teacher teacher = new Teacher();
        teacher.setSubject("Mathematics");

        assertEquals("Mathematics", teacher.getSubject());
    }

    @Test
    void testToStringContainsData() {
        Teacher teacher = new Teacher("Tomasz", "Kowal", "t.kowal@edu.pl", "abc123", LocalDate.now(), "Chemistry");
        String result = teacher.toString();

        assertTrue(result.contains("Tomasz"));
        assertTrue(result.contains("Kowal"));
        assertTrue(result.contains("Chemistry"));
    }
}
