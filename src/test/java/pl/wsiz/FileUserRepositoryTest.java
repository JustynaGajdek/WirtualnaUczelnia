package pl.wsiz;

import org.junit.jupiter.api.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileUserRepositoryTest {
    private static final String TEST_FILE = "test_users.json";
    private FileUserRepository fileUserRepository;

    @BeforeEach
    void setUp() {
        fileUserRepository = new FileUserRepository(TEST_FILE);
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
        User user = new User("Jan", "Kowalski", "jan.kowalski@example.com", "asd123", LocalDate.of(1990, 1, 1));

        boolean result = fileUserRepository.insert(user);
        assertTrue(result, "Użytkownik powinien zostać dodany.");

        List<User> users = fileUserRepository.findAll();
        assertEquals(1, users.size(), "Lista powinna zawierać 1 użytkownika.");
        assertEquals("Jan", users.get(0).getFirstName(), "Imię powinno być 'Jan'.");
        assertEquals("Kowalski", users.get(0).getLastName(), "Nazwisko powinno być 'Kowalski'.");
    }

    @Test
    void findAllUsers() {
        User user1 = new User("Alicja", "Rak", "alicja@onet.com", "asd", LocalDate.of(1995, 6, 15));
        User user2 = new User("Jan", "Kot", "jan@onet.com", "zxc", LocalDate.of(1988, 12, 5));

        fileUserRepository.insert(user1);
        fileUserRepository.insert(user2);

        List<User> users = fileUserRepository.findAll();

        assertEquals(2, users.size(), "Lista powinna zawierać 2 użytkowników.");
        assertEquals("Alicja", users.get(0).getFirstName(), "Imię pierwszego użytkownika niepoprawne.");
        assertEquals("Jan", users.get(1).getFirstName(), "Imię drugiego użytkownika niepoprawne.");
    }

    @Test
    void insertDuplicateEmail() {
        User user1 = new User("Piotr", "Nowak", "piotr@example.com", "qwe", LocalDate.of(1985, 10, 10));
        User user2 = new User("Marek", "Wiśniewski", "piotr@example.com", "asd", LocalDate.of(1989, 3, 25));

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
        User user = new User("Anna", "Dąbrowska", "anna@example.com", "123asd", LocalDate.of(1993, 7, 20));

        fileUserRepository.insert(user);
        List<User> users = fileUserRepository.findAll();

        assertNotEquals("bezpieczneHaslo", users.get(0).getPassword(), "Hasło nie powinno być jawne.");
    }
}
