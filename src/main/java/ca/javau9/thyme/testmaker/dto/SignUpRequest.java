package ca.javau9.thyme.testmaker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Email
    private String email;
}
//  This class represents the data
//  that a client might send when making a sign-up request to a server.