package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParticipationDto {
    private String created;
    private long event;
    private long id;
    private long requester;
    private String state;
}
