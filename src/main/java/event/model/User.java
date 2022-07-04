package event.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

//    User
//    int id
//    String name
//-------------------------
//    CREATE TABLE users(   ID   INT               NOT NULL AUTO_INCREMENT,  PRIMARY KEY (ID),
//                          NAME VARCHAR (100)     NOT NULL);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private  Long id;
    @Column(name="NAME")
    private  String name;

//    @OneToMany//(fetch = FetchType.LAZY)
// ///   @JoinColumn(name = "id_user")
////    @JoinTable(name = "events",
////            joinColumns = { @JoinColumn(name = "ID") },
////            inverseJoinColumns = { @JoinColumn(name = "ID_USER") }
////    )
//    private Set<Event> eventSet = new HashSet<Event>();


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    private Set<Event> events = new HashSet<>();

    public User(){

    }

    public User(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEvents(Set<Event> events) {
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

