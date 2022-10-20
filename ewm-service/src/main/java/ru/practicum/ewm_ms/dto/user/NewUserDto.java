package ru.practicum.ewm_ms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NewUserDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
}
