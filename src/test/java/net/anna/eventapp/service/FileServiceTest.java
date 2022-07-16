package net.anna.eventapp.service;

import net.anna.eventapp.model.FileEntity;
import net.anna.eventapp.repository.FileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

class FileServiceTest{
    @Mock
    FileRepository fileRepository;

    FileService fileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        fileService = new FileService();
    }

    @Test
    public void save() {
        ReflectionTestUtils.setField(fileService, "fileRepository", fileRepository);
        FileEntity file = new FileEntity();
        file.setPath("khhkkhkkh");
        Mockito.when(fileRepository.save(Mockito.any(FileEntity.class))).thenReturn(file);
        assertEquals(file.toString(), fileService.save("khhkkhkkh").toString());
    }

    @Test
    public void getById() {
        ReflectionTestUtils.setField(fileService, "fileRepository", fileRepository);
        FileEntity file = new FileEntity();
        file.setPath("khhkkhkkh");
        file.setId(35L);
        Mockito.when(fileRepository.getById(35l)).thenReturn(file);
        assertEquals(file.toString(), fileService.getById(35l).toString());
    }

    @Test
    public void getAll(){
        ReflectionTestUtils.setField(fileService, "fileRepository", fileRepository);
        List<FileEntity> fileEntities = new ArrayList<>();
        FileEntity file = new FileEntity();
        file.setPath("khhkkhkkh");
        fileEntities.add(file);
        Mockito.when(fileRepository.getAll()).thenReturn(fileEntities);
        assertEquals(fileEntities.toString(), fileService.getAll().toString());
    }

    @Test
    public void deleteById() {
        ReflectionTestUtils.setField(fileService, "fileRepository", fileRepository);
        doNothing().when(fileRepository).deleteById(15l);
        fileService.deleteById(15l);
        Mockito.verify(fileRepository,Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void update() {
        ReflectionTestUtils.setField(fileService, "fileRepository", fileRepository);
        FileEntity file = new FileEntity();
        file.setPath("khhkkhkkh");
        Mockito.when(fileRepository.update(Mockito.any(FileEntity.class))).thenReturn(file);
        assertEquals(file.toString(), fileService.update(Mockito.anyLong(),"khhkkhkkh").toString());
    }
}