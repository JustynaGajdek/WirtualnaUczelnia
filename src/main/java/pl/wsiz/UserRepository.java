package pl.wsiz;

import java.util.List;

public interface UserRepository {

    boolean insert(User user);
    List<User> findAll();

}
