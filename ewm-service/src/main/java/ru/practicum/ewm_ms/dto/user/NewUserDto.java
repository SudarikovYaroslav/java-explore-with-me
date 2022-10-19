package ru.practicum.ewm_ms.dto.user;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NewUserDto {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String name;
}
