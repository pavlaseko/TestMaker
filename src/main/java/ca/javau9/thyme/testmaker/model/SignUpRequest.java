package ca.javau9.thyme.testmaker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SignUpRequest {
    private String nickname;
    private String email;
    private String password;
}
