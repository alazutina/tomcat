package net.anna.eventapp.service;

import net.anna.eventapp.repository.EventRepository;
import net.anna.eventapp.repository.hibernate.HibernateEvent;
import net.anna.eventapp.model.EventEntity;
import net.anna.eventapp.model.FileEntity;
import net.anna.eventapp.model.UserEntity;


import java.util.List;

public class EventService {

    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new HibernateEvent();
    }

    public EventService(EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    public EventEntity save (String action, FileEntity file, UserEntity user){
        EventEntity event = new EventEntity();
        event.setAction(action);
        event.setFileEntity(file);
        event.setUserEntity(user);
        EventEntity event1  = eventRepository.save(event);
        return event1;
    }

    public List<EventEntity> getAll(){
        return eventRepository.getAll();
    }

    public EventEntity getById(Long id){
        return eventRepository.getById(id);
    }

    public void deleteById(Long id) { eventRepository.deleteById(id); }

    public EventEntity update(Long id, String action, FileEntity file, UserEntity user) {
        EventEntity event = new EventEntity();
        event.setId(id);
        event.setAction(action);
        event.setFileEntity(file);
        event.setUserEntity(user);
        EventEntity event1  = eventRepository.update(event);
        return event1;
    }

}
