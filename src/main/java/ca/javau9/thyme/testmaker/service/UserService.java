package ca.javau9.thyme.testmaker.service;

import ca.javau9.thyme.testmaker.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByNickname(String nickname);

    User saveUser(User user);

    List<User> getAllUsers();

    boolean hasUserWithNickname(String nickname);

    User validateAndGetUserByNickname(String nickname);

    void deleteUser(User user);

    boolean hasUserWithEmail(String email);

    Optional<User> validUsernameAndPassword(String username, String password);
}
