package pl.wsiz;

import pl.wsiz.model.Administrator;
import pl.wsiz.model.Student;
import pl.wsiz.model.Teacher;
import pl.wsiz.model.User;
import pl.wsiz.repo.FileUserRepository;
import pl.wsiz.view.AdministratorMenuView;
import pl.wsiz.view.LoginView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        System.out.println(System.getenv());


        User user1 = new Student("Adam", "Nowak", "adamn@onet.eu",
                "adam@34", LocalDate.of(1992, 5, 20), 1234);

        User user2 = new Teacher("Jan", "Kowalski", "janekk@gmail.com",
                "jann4#1@34", LocalDate.of(2002, 2, 12), "mgr");

        User user3 = new Administrator("Edyta", "Nowak", "edyta.nowak1@gmail.com",
                "edyta#%551", LocalDate.of(1990, 1, 3));

        Administrator user4 = new Administrator("Andrzej", "Nowak", "andrzej.nowak@gmail.com",
                "andrzej#%51xxa", LocalDate.of(1992, 10, 25));
        Teacher teacher1 = new Teacher("Zenon", "Lis", "zen@gmail.com",
                "jann4#1@34", LocalDate.of(2002, 2, 12), "mgr");
        Teacher teacher2 = new Teacher("Barbara", "Cieśla", "basia@gmail.com",
                "jann4#1@34", LocalDate.of(2002, 2, 12), "prof.");
        Student stud1 = new Student("Karolina", "Szal", "edyta.1@gmail.com",
                "edyta#%551", LocalDate.of(1990, 1, 3), 456);
        Student stud2 = new Student("Bart", "Kot", "bart1@gmail.com",
                "edyta#%551", LocalDate.of(1990, 1, 3), 459);


        FileUserRepository fileUserRepository = new FileUserRepository();

        fileUserRepository.insert(user1);
        fileUserRepository.insert(user2);
        fileUserRepository.insert(user3);
        fileUserRepository.insert(user4);
        fileUserRepository.insert(teacher1);
        fileUserRepository.insert(teacher2);
        fileUserRepository.insert(stud1);
        fileUserRepository.insert(stud2);

        LoginView loginView = new LoginView(fileUserRepository);
        loginView.login();
        User loggedUser = loginView.getLoggedUser();
        System.out.println("\nZalogowano jako:");

        printUser(loggedUser);

        if (loggedUser instanceof Administrator) {
            new AdministratorMenuView(fileUserRepository).initialize();
        }


    }

    private static void printUser(User user) {
        System.out.println("Imię i nazwisko: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Adres email: " + user.getEmail());
        String europeanDatePattern = "dd.MM.yyyy";
        DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
        System.out.println("Data urodzenia: " + user.getDateOfBirth().format(europeanDateFormatter));

        if (user instanceof Student) {
            System.out.println("Rola: Student");
        } else if (user instanceof Teacher) {
            System.out.println("Rola: Nauczyciel");
        } else if (user instanceof Administrator) {
            System.out.println("Rola: Administrator");
        } else {
            throw new RuntimeException("Nieznany typ roli użytkownika");
        }
    }
}


