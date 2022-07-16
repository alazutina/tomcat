package net.anna.eventapp.service;

import net.anna.eventapp.model.EventEntity;
import net.anna.eventapp.model.FileEntity;
import net.anna.eventapp.model.UserEntity;
import net.anna.eventapp.repository.EventRepository;
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

class EventServiceTest{
    @Mock
    EventRepository eventRepository;

    EventService eventService;
    FileService fileService;
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        eventService = new EventService();
        fileService = new FileService();
        userService = new UserService();
    }

    @Test
    public void save() {
        ReflectionTestUtils.setField(eventService, "eventRepository", eventRepository);
        EventEntity event = new EventEntity();
        event.setAction("reading");
        FileEntity file = fileService.getById(9L);
        event.setFileEntity(file);
        UserEntity user = userService.getById(9L);
        event.setUserEntity(user);
        Mockito.when(eventRepository.save(Mockito.any(EventEntity.class))).thenReturn(event);
        assertEquals(event.toString(), eventService.save("reading",file,user).toString());
    }

    @Test
    public void getById() {
        ReflectionTestUtils.setField(eventService, "eventRepository", eventRepository);
        EventEntity event = new EventEntity();
        event.setAction("reading");
        FileEntity file = new FileEntity();
        file.setPath("khhkkhkkh");
        event.setFileEntity(file);
        UserEntity user = new UserEntity();
        user.setName("Anna");
        event.setUserEntity(user);
        event.setId(20L);
        Mockito.when(eventRepository.getById(20l)).thenReturn(event);
        assertEquals(event.toString(), eventService.getById(20l).toString());
    }

    @Test
    public void getAll(){
        ReflectionTestUtils.setField(eventService, "eventRepository", eventRepository);
        List<EventEntity> eventEntities = new ArrayList<>();
        EventEntity event = new EventEntity();
        event.setAction("reading");
        FileEntity file = new FileEntity();
        file.setPath("khhkkhkkh");
        event.setFileEntity(file);
        UserEntity user = new UserEntity();
        user.setName("Anna");
        event.setUserEntity(user);
        event.setId(20L);
        eventEntities.add(event);
        Mockito.when(eventRepository.getAll()).thenReturn(eventEntities);
        assertEquals(eventEntities.toString(), eventService.getAll().toString());
    }

    @Test
    public void deleteById() {
        ReflectionTestUtils.setField(eventService, "eventRepository", eventRepository);
        doNothing().when(eventRepository).deleteById(15l);
        eventService.deleteById(15l);
        Mockito.verify(eventRepository,Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void update() {
        ReflectionTestUtils.setField(eventService, "eventRepository", eventRepository);
        EventEntity event = new EventEntity();
        event.setAction("reading");
        FileEntity file = new FileEntity();
        file.setPath("khhkkhkkh");
        event.setFileEntity(file);
        UserEntity user = new UserEntity();
        user.setName("Anna");
        event.setUserEntity(user);
        Mockito.when(eventRepository.update(Mockito.any(EventEntity.class))).thenReturn(event);
        assertEquals(event.toString(), eventService.update(Mockito.anyLong(),"reading",file,user).toString());
    }
}