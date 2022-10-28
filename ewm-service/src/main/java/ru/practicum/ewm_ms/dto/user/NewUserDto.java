package ru.practicum.ewm_ms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm_ms.util.CommonValidMarker;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@Validated
@AllArgsConstructor
public class NewUserDto {
    @Email(groups = CommonValidMarker.class)
    @NotBlank(groups = CommonValidMarker.class)
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
