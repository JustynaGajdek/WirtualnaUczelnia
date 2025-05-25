package pl.wsiz.view;

import pl.wsiz.model.Student;
import pl.wsiz.model.Teacher;
import pl.wsiz.repo.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddTeacherView {


    private final UserRepository userRepository;

    public AddTeacherView(UserRepository userRepository) {
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

        LocalDate birthDate = null;
        while (birthDate == null) {
            System.out.print("Podaj datę urodzenia (rrrr-mm-dd): ");
            String dateText = scanner.nextLine();
            try {
                birthDate = LocalDate.parse(dateText);
            } catch (DateTimeParseException e) {
                System.out.println("Niepoprawny format daty. Spróbuj ponownie.");
            }
        }


        System.out.println("Tytuł naukowy:");
        String academicDegree = scanner.nextLine();

        Teacher newTeacher = new Teacher(firstName, lastName, email, password, birthDate, academicDegree);
        boolean success = userRepository.insert(newTeacher);

        if (success) {
            System.out.println("Nauczyciel został dodany.");
        } else {
            System.out.println("Nie udało się dodać nauczyciela.");
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

}
