package ru.practicum.ewm_ms.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm_ms.util.CommonValidMarker;
import ru.practicum.ewm_ms.util.PatchValidMarker;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EventPatchDto {

    public static final int MIN_ANNOTATION_LEN = 20;
    public static final int MAX_ANNOTATION_LEN = 2000;
    public static final int MIN_DESCRIPTION_LEN = 20;
    public static final int MAX_DESCRIPTION_LEN = 7000;
    public static final int MIN_TITLE_LEN = 3;
    public static final int MAX_TITLE_LEN = 120;

    @Length(min = MIN_ANNOTATION_LEN, max = MAX_ANNOTATION_LEN, groups = CommonValidMarker.class)
    private String annotation;
    private Long category;
    @Length(min = MIN_DESCRIPTION_LEN, max = MAX_DESCRIPTION_LEN, groups = CommonValidMarker.class)
    private String description;
    private String eventDate;
    @NotNull(groups = PatchValidMarker.class)
    private Long eventId;
    private Boolean paid;
    private Integer participantLimit;
    @Length(min = MIN_TITLE_LEN, max = MAX_TITLE_LEN, groups = CommonValidMarker.class)
    private String title;

    @Override
    public String toString() {
        return "EventPatchDto{" +
                "annotation='" + annotation + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", id=" + eventId +
                ", paid=" + paid +
                ", participantLimit=" + participantLimit +
                ", title='" + title + '\'' +
                '}';
    }
}
