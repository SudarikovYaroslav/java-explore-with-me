package ru.practicum.ms.dto.comment;

import lombok.*;
import ru.practicum.ms.dto.event.EventShortDto;
import ru.practicum.ms.dto.user.UserShortDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String text;
    private UserShortDto owner;
    private EventShortDto event;
    private String date;
}
