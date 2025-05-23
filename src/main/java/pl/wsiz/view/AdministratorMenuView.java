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

                if (chosen == AdministratorMenuItem.USER_LIST.getNumber()) {
                    new UserListView(userRepository).display();
                }

            } while (chosen != AdministratorMenuItem.EXIT.getNumber());

            System.out.println("Zakończono działanie menu administratora.");
        }
    }