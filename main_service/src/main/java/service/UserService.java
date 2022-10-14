package service;

import dto.user.NewUserDto;
import dto.user.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findUsers(Integer[]ids, Integer from, Integer size);

    UserDto postUser(NewUserDto dto);

    void deleteUser(Long userId);
}
