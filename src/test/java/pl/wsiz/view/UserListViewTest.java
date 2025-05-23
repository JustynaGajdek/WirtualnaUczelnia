package pl.wsiz.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.wsiz.model.Student;
import pl.wsiz.model.Teacher;
import pl.wsiz.model.User;
import pl.wsiz.repo.UserRepository;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserListViewTest {

    @Test
    void testWithSpaces_addsCorrectPadding() {
        UserListView view = new UserListView(null);
        String result = view.withSpaces("Jan", 10);

        assertEquals("| Jan        ", result);
    }

    @ParameterizedTest
    @CsvSource({
            "Jan,10,'| Jan        '",
            "Anna,8,'| Anna     '",
            "'',5,'|       '",
            "Tomasz,10,'| Tomasz     '"
    })

    void testWithSpaces_param(String input, int width, String expected) {
        UserListView view = new UserListView(null);
        String result = view.withSpaces(input, width);

        assertEquals(expected, result);
    }

    @Test
    void testDisplay_showsFormattedUserList() {
        User user1 = new Student("Jan", "Kowalski", "jan@ex.com", "123", LocalDate.of(2000, 1, 1), 1001);
        User user2 = new Teacher("Anna", "Nowak", "anna@ex.com", "456", LocalDate.of(1990, 2, 2), "dr");

        UserRepository fakeRepo = new UserRepository() {
            @Override
            public List<User> findAll() {
                return List.of(user1, user2);
            }

            @Override
            public boolean insert(User user) {
                return true;
            }
        };

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        UserListView view = new UserListView(fakeRepo);

        // Act
        view.display();

        // Assert
        String output = outputStream.toString();
        assertTrue(output.contains("Jan"));
        assertTrue(output.contains("Kowalski"));
        assertTrue(output.contains("Student"));

        assertTrue(output.contains("Anna"));
        assertTrue(output.contains("Nowak"));
        assertTrue(output.contains("Nauczyciel"));

        System.setOut(System.out);
    }

}