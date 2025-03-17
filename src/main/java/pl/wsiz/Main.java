package pl.wsiz;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        User user1 = new User("Adam", "Nowak", "adam@onet.eu",
                "adam@34", LocalDate.of(1992, 5, 20));

        User user2 = new User("Jan", "Kowalski", "janek@gmail.com",
                "jann4#1@34", LocalDate.of(2002, 2, 12));

        User user3 = new User("Edyta", "Nowak", "edyta.nowak@gmail.com",
                "edyta#%551", LocalDate.of(1990, 1, 3));

        List<User> users = new LinkedList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        final String FILE_NAME = "users.json";
        FileUserRepository fileUserRepository = new FileUserRepository(FILE_NAME);

        fileUserRepository.insert(user1);
        fileUserRepository.insert(user2);
        fileUserRepository.insert(user3);
        fileUserRepository.findAll();

        LoginView loginView = new LoginView(fileUserRepository);
        loginView.login();


        }
    }

