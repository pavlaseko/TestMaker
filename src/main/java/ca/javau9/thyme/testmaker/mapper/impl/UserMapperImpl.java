package ca.javau9.thyme.testmaker.mapper.impl;

import ca.javau9.thyme.testmaker.dto.UserDto;
import ca.javau9.thyme.testmaker.mapper.UserMapper;
import ca.javau9.thyme.testmaker.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
    //  This class will help to get current Users info
}
