package ru.practicum.ewm_ms.service;

import ru.practicum.ewm_ms.dto.user.NewUserDto;
import ru.practicum.ewm_ms.dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findUsers(List<Long> ids, Integer from, Integer size);

    UserDto postUser(NewUserDto dto);

    void deleteUser(Long userId);
}
