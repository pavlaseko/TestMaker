package ca.javau9.thyme.testmaker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
//  This class represents the data
//  that a client might send when making a log-in request to a server.
