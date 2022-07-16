package net.anna.eventapp.dto;

import lombok.Builder;
import lombok.Data;
import net.anna.eventapp.model.EventEntity;

@Data
@Builder
public class EventDto {
    private Long id;
    private String action;
    private FileDto fileDto;
    private UserDto userDto;

    public EventEntity toEntity () {
        return null;
    }

    public static EventDto fromEntity(EventEntity eventEntity) {
        return EventDto.builder()
                .id(eventEntity.getId())
                .action(eventEntity.getAction())
                .fileDto(FileDto.fromEntity(eventEntity.getFile()))
                .userDto(UserDto.fromEntity(eventEntity.getUser()))
                .build();
    }
}