package pl.wsiz.repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;
import pl.wsiz.model.User;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class FileUserRepository implements UserRepository {

    private static final String FILE_NAME = "users.json";
    private final ObjectMapper objectMapper;

    public FileUserRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    public boolean insert(User user) {
        List<User> users = findAll();

        if (user.getEmail() == null || user.getEmail().isBlank()) return false;


        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                System.out.println("Użytkownik o podanym adresie e-mail już istnieje w systemie. Rejestracja nie została wykonana.");
                return false;
            }
        }

        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        users.add(user);

        try {
            String json = objectMapper
                    .writerFor(new TypeReference<List<User>>(){})
                    .writeValueAsString(users);
            try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
                fos.write(json.getBytes(StandardCharsets.UTF_8));
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Błąd zapisu JSON‑a", e);
        }
    }

    @Override
    public List<User> findAll() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME)) {
            byte[] bytes = fis.readAllBytes();
            return objectMapper.readValue(bytes, new TypeReference<List<User>>() {});
        } catch (FileNotFoundException e) {
            return new LinkedList<>();
        } catch (IOException e) {
            throw new RuntimeException("Błąd odczytu.", e);
        }
    }
}
