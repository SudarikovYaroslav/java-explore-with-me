package ru.practicum.ewm_ms.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm_ms.util.PatchValidMarker;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Validated
@AllArgsConstructor
public class EventPatchDto {
    private String annotation;
    private Long category;
    private String description;
    private String eventDate;
    @NotNull(groups = PatchValidMarker.class)
    private Long eventId;
    private Boolean paid;
    private Integer participantLimit;
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
