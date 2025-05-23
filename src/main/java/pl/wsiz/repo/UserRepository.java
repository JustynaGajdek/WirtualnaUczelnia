package pl.wsiz.repo;

import pl.wsiz.model.User;

import java.util.List;

public interface UserRepository {

    boolean insert(User user);
    List<User> findAll();

}
