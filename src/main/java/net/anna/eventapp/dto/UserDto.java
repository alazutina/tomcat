package net.anna.eventapp.dto;

import lombok.Builder;
import lombok.Data;
import net.anna.eventapp.model.FileEntity;
import net.anna.eventapp.model.EventEntity;
import net.anna.eventapp.model.UserEntity;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;

    public UserEntity toEntity () {
        return null;
    }

    public static UserDto fromEntity(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }
}
