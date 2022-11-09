package ru.practicum.ms.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ms.util.PatchValidMarker;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentPatchDto {

    private static final int MIN_TEXT_LEN = 10;
    private static final int MAX_TEXT_LEN = 1024;

    @NotNull(groups = PatchValidMarker.class)
    private Long id;
    @Length(min = MIN_TEXT_LEN, max = MAX_TEXT_LEN, groups = PatchValidMarker.class)
    private String text;
    private Long ownerId;
    private Long eventId;

    @Override
    public String toString() {
        return "CommentPatchDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", ownerId=" + ownerId +
                ", eventId=" + eventId +
                '}';
    }
}
