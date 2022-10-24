package ru.practicum.ewm_ms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
    @Email
    private String email;
    private Long id;
    private String name;
}
