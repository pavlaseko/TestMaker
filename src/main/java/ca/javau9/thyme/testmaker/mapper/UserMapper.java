package ca.javau9.thyme.testmaker.mapper;

import ca.javau9.thyme.testmaker.dto.UserDto;
import ca.javau9.thyme.testmaker.model.User;

public interface UserMapper {

    UserDto toUserDto(User user);
}