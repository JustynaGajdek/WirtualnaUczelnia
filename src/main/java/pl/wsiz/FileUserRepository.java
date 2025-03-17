package pl.wsiz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUserRepository implements UserRepository {
    private final String fileName;
    private final ObjectMapper objectMapper;

    public FileUserRepository(String fileName) {
        this.fileName = fileName;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    public boolean insert(User user) {
        List<User> users = findAll();

        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                System.out.println("Użytkownik o podanym adresie e-mail już istnieje w systemie. Rejestracja nie została wykonana.");
                return false;
            }
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User hashedUser = new User(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                hashedPassword,
                user.getDateOfBirth()
        );
        users.add(hashedUser);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), users);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas zapisu użytkowników do pliku JSON", e);
        }
    }

    @Override
    public List<User> findAll() {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas odczytu użytkowników z pliku JSON", e);
        }
    }
}
