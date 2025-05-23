package pl.wsiz.model;

import org.junit.jupiter.api.Test;
import pl.wsiz.model.Administrator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    @Test
    void testConstructorAndGetters() {
        LocalDate birthDate = LocalDate.of(1985, 3, 15);
        Administrator admin = new Administrator("Ewa", "Nowak", "ewa@admin.com", "tajne123", birthDate);

        assertEquals("Ewa", admin.getFirstName());
        assertEquals("Nowak", admin.getLastName());
        assertEquals("ewa@admin.com", admin.getEmail());
        assertEquals("tajne123", admin.getPassword());
        assertEquals(birthDate, admin.getDateOfBirth());
    }

    @Test
    void testSetPassword() {
        Administrator admin = new Administrator();
        admin.setPassword("noweHaslo");

        assertEquals("noweHaslo", admin.getPassword());
    }

    @Test
    void testToStringContainsName() {
        Administrator admin = new Administrator("Krzysztof", "Admin", "kris@adm.com", "admin1", LocalDate.now());
        String output = admin.toString();

        assertTrue(output.contains("Krzysztof"));
        assertTrue(output.contains("Admin"));
    }
}
