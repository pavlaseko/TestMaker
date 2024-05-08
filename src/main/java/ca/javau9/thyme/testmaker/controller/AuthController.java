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
@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    // LOG-IN LOGIC
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.validUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(new AuthResponse(user.getId(), user.getName(), user.getRole()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       // This method accepts a LoginRequest object in the request body and returns a ResponseEntity containing an AuthResponse.
       // Inside the method, it validates the username and password
    }

    // SIGN-UP LOGIC
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new DuplicatedUserInfoException(String.format("Username %s is already been used", signUpRequest.getUsername()));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new DuplicatedUserInfoException(String.format("Email %s is already been used", signUpRequest.getEmail()));
        }

        User user = userService.saveUser(createUser(signUpRequest));
        return new AuthResponse(user.getId(), user.getName(), user.getRole());
        // This method  accepts a SignUpRequest object in the request body and returns an AuthResponse.
        // Inside the method, it checks if the username or email is already in use.
    }

    private User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(WebSecurityConfig.USER);
        return user;
        // This is used internally by the signUp method to create a User object from a SignUpRequest.
    }
}


