package pl.wsiz.view;

import pl.wsiz.repo.UserRepository;

import java.util.Scanner;

public class AdministratorMenuView {
        private final UserRepository userRepository;

        public AdministratorMenuView(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public void initialize() {
            int chosen;
            Scanner scanner = new Scanner(System.in);

            do {
                System.out.println("====================");
                System.out.println("MENU ADMINISTRATORA");
                System.out.println("====================");

                for (AdministratorMenuItem item : AdministratorMenuItem.values()) {
                    System.out.println(item.getNumber() + " - " + item.getDescriptionPl());
                }

                System.out.print("Co wybierasz? ");
                chosen = scanner.nextInt();
                scanner.nextLine();

                switch (chosen) {
                    case 1 -> new UserListView(userRepository).display();
                    case 2 -> new AddStudentView(userRepository).initialize();
                    case 3 -> new AddTeacherView(userRepository).initialize();
                    case 4 -> new AddAdminView(userRepository).initialize();
                }


            } while (chosen != AdministratorMenuItem.EXIT.getNumber());

            System.out.println("Zakończono działanie menu administratora.");
        }
    }