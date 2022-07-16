package net.anna.eventapp.service;

import net.anna.eventapp.repository.UserRepository;
import net.anna.eventapp.repository.hibernate.HibernateUser;
import net.anna.eventapp.model.UserEntity;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new HibernateUser();
    }

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserEntity save (String name){
        UserEntity user = new UserEntity();
        user.setName(name);
        UserEntity user1 = userRepository.save(user);
        return user1;
    }

    public List<UserEntity> getAll(){
        return userRepository.getAll();
    }


    public void deleteById(Long id) { userRepository.deleteById(id); }

    public UserEntity getById(Long id){
        return userRepository.getById(id);
    }

    public UserEntity update(Long id, String name) {
        UserEntity user = new UserEntity();
        user.setId(id);
        user.setName(name);
        return userRepository.update(user);
    }
}
