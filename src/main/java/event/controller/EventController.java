package event.controller;

import event.model.Event;
import event.model.File;
import event.model.User;
import event.repository.EventRepository;
import event.repository.hibernate.HibernateEvent;


import java.util.List;

public class EventController {

    private final EventRepository eventRepository;

    public EventController() {
        this.eventRepository = new HibernateEvent();
    }

    public EventController(EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    public Event save (String action, File file, User user){
        Event event = new Event();
        event.setAction(action);
        event.setFile(file);
        event.setUser(user);
        Event event1  = eventRepository.save(event);
        return event1;
    }

    public List<Event> getAll(){
        return eventRepository.getAll();
    }

    public Event getById(Long id){
        return eventRepository.getById(id);
    }

    public void deleteById(Long id) { eventRepository.deleteById(id); }

    public Event update(Long id, String action, File file, User user) {
        Event event = new Event();
        event.setId(id);
        event.setAction(action);
        event.setFile(file);
        event.setUser(user);
        Event event1  = eventRepository.update(event);
        return event1;
    }

}
