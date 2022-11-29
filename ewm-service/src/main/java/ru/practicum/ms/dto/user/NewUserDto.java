package ru.practicum.ms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.ms.util.CommonValidMarker;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NewUserDto {
    @Email(groups = CommonValidMarker.class)
    @NotNull(groups = CommonValidMarker.class)
    private String email;
    @NotBlank(groups = CommonValidMarker.class)
    private String name;

    @Override
    public String toString() {
        return "NewUserDto{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
