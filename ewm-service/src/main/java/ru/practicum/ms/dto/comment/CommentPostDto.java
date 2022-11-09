package ru.practicum.ms.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CommentPostDto {

    private static final int MIN_TEXT_LEN = 10;
    private static final int MAX_TEXT_LEN = 1024;

    @NotBlank
    @Length(min = MIN_TEXT_LEN, max = MAX_TEXT_LEN)
    private String text;
    @NotNull
    private Long eventId;

    @Override
    public String toString() {
        return "CommentPostDto{" +
                "text='" + text + '\'' +
                ", eventId=" + eventId +
                '}';
    }
}
