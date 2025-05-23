package pl.wsiz.repo;

import org.junit.jupiter.api.*;
import pl.wsiz.model.Administrator;
import pl.wsiz.model.Student;
import pl.wsiz.model.Teacher;
import pl.wsiz.model.User;
import pl.wsiz.repo.FileUserRepository;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileUserRepositoryTest {
    private static final String TEST_FILE = "users.json";
    private FileUserRepository fileUserRepository;

    @BeforeEach
    void setUp() {
        fileUserRepository = new FileUserRepository();
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void insertNewUser() {
        Student user = new Student("Jan", "Kowalski", "jan.kowalski@onet.com", "asd123", LocalDate.of(1990, 1, 1), 12365);

        boolean result = fileUserRepository.insert(user);
        assertTrue(result, "Użytkownik powinien zostać dodany.");

        List<User> users = fileUserRepository.findAll();
        assertEquals(1, users.size(), "Lista powinna zawierać 1 użytkownika.");
        assertEquals("Jan", users.get(0).getFirstName(), "Imię powinno być 'Jan'.");
        assertEquals("Kowalski", users.get(0).getLastName(), "Nazwisko powinno być 'Kowalski'.");
    }

    @Test
    void findAllUsers() {
        Teacher user1 = new Teacher("Alicja", "Rak", "alicja@onet.com", "asd", LocalDate.of(1995, 6, 15), "Math" );
        Administrator user2 = new Administrator("Jan", "Kot", "jan@onet.com", "zxc", LocalDate.of(1988, 12, 5));

        fileUserRepository.insert(user1);
        fileUserRepository.insert(user2);

        List<User> users = fileUserRepository.findAll();

        assertEquals(2, users.size(), "Lista powinna zawierać 2 użytkowników.");
        assertEquals("Alicja", users.get(0).getFirstName(), "Imię pierwszego użytkownika niepoprawne.");
        assertEquals("Jan", users.get(1).getFirstName(), "Imię drugiego użytkownika niepoprawne.");
    }

    @Test
    void insertDuplicateEmail() {
        Student user1 = new Student("Piotr", "Nowak", "piotr@example.com", "qwe", LocalDate.of(1985, 10, 10), 2546);
        Student user2 = new Student("Marek", "Wiśniewski", "piotr@example.com", "asd", LocalDate.of(1989, 3, 25), 368);

        boolean result1 = fileUserRepository.insert(user1);
        boolean result2 = fileUserRepository.insert(user2);

        assertTrue(result1, "Pierwszy użytkownik powinien zostać dodany.");
        assertFalse(result2, "Drugi użytkownik nie powinien zostać dodany.");

        List<User> users = fileUserRepository.findAll();
        assertEquals(1, users.size(), "Lista powinna zawierać 1 użytkownika.");
    }

    @Test
    void findAllEmptyFile() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }

        List<User> users = fileUserRepository.findAll();

        assertTrue(users.isEmpty(), "Lista powinna być pusta.");
    }

    @Test
    void passwordIsHashed() {
        Administrator user = new Administrator("Anna", "Dąbrowska", "anna@example.com", "123asd", LocalDate.of(1993, 7, 20));

        fileUserRepository.insert(user);
        List<User> users = fileUserRepository.findAll();

        assertNotEquals("bezpieczneHaslo", users.get(0).getPassword(), "Hasło nie powinno być jawne.");
    }

    @Test
    void teacherToStringContainsCorrectInfo() {
        Teacher teacher = new Teacher(
                "Magdalena", "Nowak", "magda@edu.pl", "haslo123",
                LocalDate.of(1980, 5, 10), "Biology"
        );

        fileUserRepository.insert(teacher);
        List<User> users = fileUserRepository.findAll();

        assertEquals(1, users.size(), "Powinien być 1 użytkownik.");
        String output = users.get(0).toString();

        assertTrue(output.contains("Magdalena"));
        assertTrue(output.contains("Biology"));
        assertTrue(output.contains("Nowak"));
    }

    @Test
    void hashedPasswordDoesNotMatchOriginalPlaintext() {
        Student user = new Student("Ada", "Cat", "ada@edu.com", "secret123", LocalDate.of(1991, 12, 10), 54321);
        fileUserRepository.insert(user);

        User saved = fileUserRepository.findAll().get(0);
        assertNotEquals("secret123", saved.getPassword());
        assertTrue(saved.getPassword().startsWith("$2a$"));
    }

    @Test
    void insertFailsForUserWithEmptyEmail() {
        Administrator admin = new Administrator("Eva", "Zero", "", "haslo", LocalDate.of(1990, 3, 3));
        boolean result = fileUserRepository.insert(admin);
        assertFalse(result, "Nie można zapisać użytkownika bez emaila.");
    }

    @Test
    void insertingMultipleUsersIncreasesListSize() {
        int initialSize = fileUserRepository.findAll().size();

        fileUserRepository.insert(new Administrator("Jan", "Kot", "j.kot@admin.com", "abc123", LocalDate.of(2000, 1, 1)));
        fileUserRepository.insert(new Student("Ewa", "Lis", "ewa@edu.com", "abc32", LocalDate.of(2000, 1, 1), 5555));

        int newSize = fileUserRepository.findAll().size();
        assertEquals(initialSize + 2, newSize);
    }


}
