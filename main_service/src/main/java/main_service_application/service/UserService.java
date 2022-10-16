package main_service_application.service;

import main_service_application.dto.user.NewUserDto;
import main_service_application.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findUsers(Long[]ids, Integer from, Integer size);

    UserDto postUser(NewUserDto dto);

    void deleteUser(Long userId);
}
