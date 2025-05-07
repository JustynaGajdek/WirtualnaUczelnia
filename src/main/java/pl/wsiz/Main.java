package pl.wsiz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        System.out.println(System.getenv());


        User user1 = new Student("Adam", "Nowak", "adamn@onet.eu",
                "adam@34", LocalDate.of(1992, 5, 20), 1234);

        User user2 = new Teacher("Jan", "Kowalski", "janekk@gmail.com",
                "jann4#1@34", LocalDate.of(2002, 2, 12), "Math");

        User user3 = new Administrator("Edyta", "Nowak", "edyta.nowak1@gmail.com",
                "edyta#%551", LocalDate.of(1990, 1, 3));

        Administrator user4 = new Administrator("Andrzej", "Nowak", "andrzej.nowak@gmail.com",
                "andrzej#%51xxa", LocalDate.of(1992, 10, 25));

        FileUserRepository fileUserRepository = new FileUserRepository();

        fileUserRepository.insert(user1);
        fileUserRepository.insert(user2);
        fileUserRepository.insert(user3);
        fileUserRepository.insert(user4);

        LoginView loginView = new LoginView(fileUserRepository);
        loginView.login();
        User loggedUser = loginView.getLoggedUser();
        System.out.println("\nZalogowano jako:");

        printUser(loggedUser);

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


