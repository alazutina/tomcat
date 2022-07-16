package net.anna.eventapp.service;


import net.anna.eventapp.model.UserEntity;
import net.anna.eventapp.repository.UserRepository;
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

class UserServiceTest{
    @Mock
    UserRepository userRepository;

    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService();
    }

    @Test
    public void save() {
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        UserEntity user = new UserEntity();
        user.setName("Anna");
        Mockito.when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(user);
        assertEquals(user.toString(), userService.save("Anna").toString());
    }

    @Test
    public void getById() {
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        UserEntity user = new UserEntity();
        user.setName("Anna");
        user.setId(35l);
        Mockito.when(userRepository.getById(35l)).thenReturn(user);
        assertEquals(user.toString(), userService.getById(35l).toString());
    }

    @Test
    public void getAll(){
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        List<UserEntity> users = new ArrayList<>();
        UserEntity user = new UserEntity();
        user.setName("Anna");
        users.add(user);
        Mockito.when(userRepository.getAll()).thenReturn(users);
        assertEquals(users.toString(), userService.getAll().toString());
    }

    @Test
    public void deleteById() {
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        doNothing().when(userRepository).deleteById(15l);
        userService.deleteById(15l);
        Mockito.verify(userRepository,Mockito.times(1)).deleteById(Mockito.any());
    }
    @Test
    public void update() {
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        UserEntity user = new UserEntity();
        user.setName("Anna");
        Mockito.when(userRepository.update(Mockito.any(UserEntity.class))).thenReturn(user);
        assertEquals(user.toString(), userService.update(Mockito.anyLong(),"Anna").toString());
    }
}

//class UserServiceTest {
//
//    UserService instance;
//
//    public UserServiceTest(){
//        instance = new UserService();
//    }
//    @Test
//    public void save() {
//        UserEntity user = new UserEntity();
//        user.setName("Anna");
//        UserEntity actual = instance.save(user.getName());
//        UserEntity expected = instance.getById(35l);
//        assertEquals(actual.toString(),expected.toString());
//
//    }
//}
