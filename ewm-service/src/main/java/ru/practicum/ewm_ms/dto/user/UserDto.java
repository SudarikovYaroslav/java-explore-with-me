package ru.practicum.ewm_ms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ewm_ms.util.CommonValidMarker;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
    @Email(groups = CommonValidMarker.class)
    private String email;
    private Long id;
    private String name;
}
