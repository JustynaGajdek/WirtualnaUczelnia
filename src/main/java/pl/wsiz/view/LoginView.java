package pl.wsiz.view;

import org.mindrot.jbcrypt.BCrypt;
import pl.wsiz.model.User;
import pl.wsiz.repo.UserRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LoginView {
    private final UserRepository userRepository;
    private User loggedUser;

    public LoginView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login() {
        String email;
        String password;

        String autologin = System.getenv("USER_AUTOLOGIN");
        if ("true".equalsIgnoreCase(autologin)) {
            email    = System.getenv("USER_EMAIL");
            password = System.getenv("USER_PASSWORD");
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("====================");
            System.out.println("EKRAN LOGOWANIA");
            System.out.print  ("Podaj adres e-mail: ");
            email = scanner.nextLine();
            System.out.print  ("Podaj hasło: ");
            password = scanner.nextLine();
        }

        while (true) {
            List<User> users = userRepository.findAll();
            for (User u : users) {
                if (u.getEmail().equalsIgnoreCase(email)
                        && BCrypt.checkpw(password, u.getPassword())) {
                    this.loggedUser = u;
                    System.out.println("\nLogowanie udane!");
                    printUserDetails(u);
                    return;
                }
            }

            System.out.println("\nLogowanie nieudane! Spróbuj ponownie!\n");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Podaj adres e-mail: ");
            email = scanner.nextLine();
            System.out.print("Podaj hasło: ");
            password = scanner.nextLine();
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }


    private void printUserDetails(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String role = user.getClass().getSimpleName();

        String info = """
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

        System.out.println(info);
    }
}