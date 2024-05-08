package ca.javau9.thyme.testmaker.controller;

import ca.javau9.thyme.testmaker.dto.UserDto;
import ca.javau9.thyme.testmaker.mapper.UserMapper;
import ca.javau9.thyme.testmaker.model.User;
import ca.javau9.thyme.testmaker.security.CustomUserDetails;
import ca.javau9.thyme.testmaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public UserDto getCurrentUser(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return userMapper.toUserDto(userService.validateAndGetUserByUsername(currentUser.getUsername()));
        //  This method returns information about the currently authenticated user.
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers().stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
        // This method accepts the currently authenticated user,
        // then retrieves the user's details by nickname and maps them to a DTO
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userMapper.toUserDto(userService.validateAndGetUserByUsername(username));
        // This method returns information about a specific user.
    }

    @DeleteMapping("/{username}")
    public UserDto deleteUser(@PathVariable String username) {
        User user = userService.validateAndGetUserByUsername(username);
        userService.deleteUser(user);
        return userMapper.toUserDto(user);
        // This method deletes a user with the given username.
    }
}
