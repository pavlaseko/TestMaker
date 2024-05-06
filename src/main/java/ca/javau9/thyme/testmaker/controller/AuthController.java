package ca.javau9.thyme.testmaker.controller;

import ca.javau9.thyme.testmaker.dto.AuthResponse;
import ca.javau9.thyme.testmaker.dto.LoginRequest;
import ca.javau9.thyme.testmaker.dto.SignUpRequest;
import ca.javau9.thyme.testmaker.exeption.DuplicatedUserInfoException;
import ca.javau9.thyme.testmaker.model.User;
import ca.javau9.thyme.testmaker.security.WebSecurityConfig;
import ca.javau9.thyme.testmaker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    // LOG-IN LOGIC
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.validUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getNickname(), user.getRole()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // SIGN-UP LOGIC
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithNickname(signUpRequest.getNickname())) {
            throw new DuplicatedUserInfoException(String.format("Username %s is already been used", signUpRequest.getNickname()));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s is already been used", signUpRequest.getEmail()));
        }

        User user = userService.saveUser(createUser(signUpRequest));
        return new AuthResponse(user.getId(), user.getNickname(), user.getRole());
    }

    private User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setNickname(signUpRequest.getNickname());
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(WebSecurityConfig.USER);
        return user;
    }
}


