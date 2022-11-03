package ru.practicum.ms.service;

import ru.practicum.ms.dto.user.NewUserDto;
import ru.practicum.ms.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findUsers(List<Long> ids, Integer from, Integer size);

    UserDto postUser(NewUserDto dto);

    void deleteUser(Long userId);
}
