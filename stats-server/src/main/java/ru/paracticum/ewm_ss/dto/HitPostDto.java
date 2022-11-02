package ru.paracticum.ewm_ss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HitPostDto {

    public static final int MAX_IP_LENGTH = 15;
    public static final int MIN_IP_LENGTH = 7;

    private Long id;
    @NotBlank
    private String app;
    @NotBlank
    private String uri;
    @NotBlank
    @Length(min = MIN_IP_LENGTH, max = MAX_IP_LENGTH)
    private String ip;
    private String timeStamp;
    @NotNull
    private Long eventId;
}
