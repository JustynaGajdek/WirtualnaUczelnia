package pl.wsiz.view;

import pl.wsiz.model.Administrator;
import pl.wsiz.model.Student;
import pl.wsiz.model.Teacher;
import pl.wsiz.model.User;
import pl.wsiz.repo.UserRepository;

import java.util.List;

public class UserListView {

    private final UserRepository userRepository;

    public UserListView(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void display() {
        System.out.println("\n======= Lista Użytkowników =======");
        System.out.println(withSpaces("Imię", 20)
                + withSpaces("Nazwisko", 20)
                + withSpaces("E-mail", 32)
                + withSpaces("Rola", 10));
        System.out.println("-".repeat(90));
        List<User> users = userRepository.findAll();
        users.sort((o1, o2) -> {
            int compareUsers = o1.getLastName().compareTo(o2.getLastName());
            if (compareUsers == 0) {
                return o1.getFirstName().compareToIgnoreCase(o2.getFirstName());
            } else {
                return compareUsers;
            }
        });
        for (User user : users) {
            String role;
            if (user instanceof Student) {
                role = "Student";
            } else if (user instanceof Teacher) {
                role = "Nauczyciel";
            } else if (user instanceof Administrator) {
                role = "Administrator";
            } else {
                throw new RuntimeException("Nieznany typ roli użytkownika");
            }
            System.out.println(withSpaces(user.getFirstName(), 20)
                    + withSpaces(user.getLastName(), 20)
                    + withSpaces(user.getEmail(), 32)
                    + withSpaces(role, 10));
        }
    }

    String withSpaces(String text, int maxLength) {
        return "| " + String.format("%-" + maxLength + "s", text) + " ";
    }

}
