package ca.javau9.thyme.testmaker.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedUserInfoException extends RuntimeException {

    //   If there is the same User found, it handles this error with custom message
    public DuplicatedUserInfoException(String message) {
        super(message);
    }
}