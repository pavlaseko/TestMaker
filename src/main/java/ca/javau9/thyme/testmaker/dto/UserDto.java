package ca.javau9.thyme.testmaker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record UserDto(Long id, String nickname, String email, String role) {
    // This is a compact Java record class for holding user-related information.
}
