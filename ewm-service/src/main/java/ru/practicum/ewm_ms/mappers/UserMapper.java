package ru.practicum.ewm_ms.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.ewm_ms.dto.user.NewUserDto;
import ru.practicum.ewm_ms.dto.user.UserDto;
import ru.practicum.ewm_ms.dto.user.UserShortDto;
import ru.practicum.ewm_ms.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toModel(NewUserDto dto) {
        return User.builder()
                .id(null)
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static UserShortDto toUserShortDto(User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}