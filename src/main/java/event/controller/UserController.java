package event.controller;

import event.model.File;
import event.model.User;
import event.repository.UserRepository;
import event.repository.hibernate.HibernateUser;

import java.util.List;

public class UserController {
    private final UserRepository userRepository;

    public UserController() {
        this.userRepository = new HibernateUser();
    }

    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User save (String name){
        User user = new User();
        user.setName(name);
        User user1 = userRepository.save(user);
        return user1;
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }


    public void deleteById(Long id) { userRepository.deleteById(id); }

    public User getById(Long id){
        return userRepository.getById(id);
    }

    public User update(Long id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return userRepository.update(user);
    }
}
