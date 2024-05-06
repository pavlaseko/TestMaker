package ca.javau9.thyme.testmaker.service.impl;

import ca.javau9.thyme.testmaker.exeption.UserNotFoundException;
import ca.javau9.thyme.testmaker.model.User;
import ca.javau9.thyme.testmaker.repository.UserRepository;
import ca.javau9.thyme.testmaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 1)  Finding User by his nickname
    @Override
    public Optional<User> getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    // 2)  Saving new User
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);}

    // 3)  Getting all Users as a list
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 4)  Checking if there is any Users with this nickname
    @Override
    public boolean hasUserWithNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    // 5)  Check if needed User exists and getting it
    @Override
    public User validateAndGetUserByNickname(String nickname) {
        return getUserByNickname(nickname)
                .orElseThrow(() -> new UserNotFoundException(String
                        .format("User with nickname %s not found", nickname)));
    }

    // 6)  Deleting User
    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // 7)  Checking if there is any Users with this email
    @Override
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // 8)  Checking if given User nickname and password matches one in database
    @Override
    public Optional<User> validUsernameAndPassword(String nickname, String password) {
        return getUserByNickname(nickname)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }
}
