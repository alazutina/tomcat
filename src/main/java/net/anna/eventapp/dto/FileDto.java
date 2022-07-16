package net.anna.eventapp.dto;

import lombok.Builder;
import lombok.Data;
import net.anna.eventapp.model.FileEntity;

@Data
@Builder
public class FileDto {
    private Long id;
    private String path;

    public FileEntity toEntity () {
        return null;
    }

    public static FileDto fromEntity(FileEntity fileEntity) {
        return FileDto.builder()
                .id(fileEntity.getId())
                .path(fileEntity.getPath())
                .build();
    }
}
