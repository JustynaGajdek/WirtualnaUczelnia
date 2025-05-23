package pl.wsiz.model;

import org.junit.jupiter.api.Test;
import pl.wsiz.model.Teacher;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate birthDate = LocalDate.of(1990, 5, 10);
        Teacher teacher = new Teacher("Anna", "Zielińska", "anna@school.com", "nauczyciel1", birthDate, "dr hab.");

        assertEquals("Anna", teacher.getFirstName());
        assertEquals("Zielińska", teacher.getLastName());
        assertEquals("anna@school.com", teacher.getEmail());
        assertEquals("nauczyciel1", teacher.getPassword());
        assertEquals(birthDate, teacher.getDateOfBirth());
        assertEquals("dr hab.", teacher.getAcademicDegree());
    }

    @Test
    void testSetSubject() {
        Teacher teacher = new Teacher();
        teacher.setAcademicDegree("mgr");

        assertEquals("mgr", teacher.getAcademicDegree());
    }

    @Test
    void testToStringContainsData() {
        Teacher teacher = new Teacher("Tomasz", "Kowal", "t.kowal@edu.pl", "abc123", LocalDate.now(), "prof.");
        String result = teacher.toString();

        assertTrue(result.contains("Tomasz"));
        assertTrue(result.contains("Kowal"));
        assertTrue(result.contains("prof."));
    }
}
