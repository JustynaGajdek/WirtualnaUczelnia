package pl.wsiz.view;

import pl.wsiz.model.Administrator;
import pl.wsiz.model.Student;
import pl.wsiz.repo.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddAdminView {


    private final UserRepository userRepository;

    public AddAdminView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Imię:");
        String firstName = scanner.nextLine();

        System.out.println("Nazwisko:");
        String lastName = scanner.nextLine();

        String email;
        while (true) {
            System.out.print("Podaj adres e-mail: ");
            email = scanner.nextLine();

            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Niepoprawny adres e-mail. Spróbuj ponownie.");
            }
        }


        System.out.println("Hasło:");
        String password = scanner.nextLine();

        LocalDate dateOfBirth = null;
        while (dateOfBirth == null) {
            System.out.print("Podaj datę urodzenia (rrrr-mm-dd): ");
            String dateText = scanner.nextLine();
            try {
                dateOfBirth = LocalDate.parse(dateText);
            } catch (DateTimeParseException e) {
                System.out.println("Niepoprawny format daty. Spróbuj ponownie.");
            }
        }

        Administrator newAdmin = new Administrator(firstName, lastName, email, password, dateOfBirth);
        boolean success = userRepository.insert(newAdmin);

        if (success) {
            System.out.println("Student został dodany.");
        } else {
            System.out.println("Nie udało się dodać studenta.");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
