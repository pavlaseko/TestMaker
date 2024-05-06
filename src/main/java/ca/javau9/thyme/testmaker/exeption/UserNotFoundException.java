package ca.javau9.thyme.testmaker.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    //   If User is not found, it handles this error with custom message
    public UserNotFoundException(String message) { super(message); }
}
