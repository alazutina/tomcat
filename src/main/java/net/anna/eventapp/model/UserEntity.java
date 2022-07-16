package net.anna.eventapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private  Long id;
    @Column(name="NAME")
    private  String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    private Set<EventEntity> events = new HashSet<>();

    public UserEntity(){

    }

    public UserEntity(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<EventEntity> getEvents() {
        return events;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEvents(Set<EventEntity> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"" +
                ",\"name\": \"" + name + "\"" +
                ", \"eventSet\": " + events +
                '}';
    }
}