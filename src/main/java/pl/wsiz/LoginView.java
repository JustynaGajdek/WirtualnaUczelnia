package pl.wsiz;

import org.mindrot.jbcrypt.BCrypt;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LoginView {
    private final FileUserRepository userRepository;
    private final Scanner scanner;

    public LoginView(FileUserRepository userRepository) {
        this.userRepository = userRepository;
        this.scanner = new Scanner(System.in);
    }

    public void login() {
        boolean isAuthenticated = false;

        while (!isAuthenticated) {
            System.out.print("Podaj login (e-mail): ");
            String email = scanner.nextLine();

            System.out.print("Podaj hasło: ");
            String password = scanner.nextLine();

            User user = authenticateUser(email, password);

            if (user != null) {
                System.out.println("\nLogowanie udane!");
                printUserDetails(user);
                isAuthenticated = true;
            } else {
                System.out.println("\nNiepoprawny login lub hasło. Spróbuj ponownie.");
            }
        }
    }

    private User authenticateUser(String email, String password) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    return user;
                }
            }
        }
        return null;
    }

    private void printUserDetails(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String userDetails = """
                --- Dane użytkownika ---
                Imię i nazwisko: %s %s
                Adres email: %s
                Data urodzenia: %s
                """.formatted(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth().format(formatter)
        );

        System.out.println(userDetails);
    }
}