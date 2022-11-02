package ru.practicum.ewm_ms.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm_ms.model.Location;
import ru.practicum.ewm_ms.util.CommonValidMarker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
public class EventPostDto {
    public static final int MIN_TITLE_LEN = 3;
    public static final int MAX_TITLE_LEN = 120;
    public static final int MIN_ANNOTATION_LEN = 20;
    public static final int MIN_DESCRIPTION_LEN = 20;
    public static final int MAX_ANNOTATION_LEN = 2000;
    public static final int MAX_DESCRIPTION_LEN = 7000;

    @NotBlank
    @Length(min = MIN_ANNOTATION_LEN, max = MAX_ANNOTATION_LEN, groups = CommonValidMarker.class)
    private String annotation;
    private Long category;
    @NotBlank
    @Length(min = MIN_DESCRIPTION_LEN, max = MAX_DESCRIPTION_LEN, groups = CommonValidMarker.class)
    private String description;
    @NotBlank
    private String eventDate;
    private Location location;
    private Boolean paid;
    @PositiveOrZero
    private Integer participantLimit;
    private Boolean requestModeration;
    @NotBlank
    @Length(min = MIN_TITLE_LEN, max = MAX_TITLE_LEN, groups = CommonValidMarker.class)
    private String title;

    @Override
    public String toString() {
        return "EventPostDto{" +
                "annotation='" + annotation + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", location=" + location +
                ", paid=" + paid +
                ", participantLimit=" + participantLimit +
                ", requestModeration=" + requestModeration +
                ", title='" + title + '\'' +
                '}';
    }
}
