package event.model;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private  Long id;
    @Column(name="ACTION")
    private  String action;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FILE", referencedColumnName = "ID")
//    @Column(name="ID_FILE")
    private File file;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER", referencedColumnName = "id")
    private User user;

    public Event(){}

    public Event(Long id, String action, File file, User user){
        this.id=id;
        this.action= action;
        this.file=file;
        this.user=user;
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public File getFile() {
        return file;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"" +
                ",\"action\": \"" + action + "\"" +
                ",\"user_id\": \"" + user.getId() + "\"" +
                ",\"file_id\": \"" + file.getId() + "\"" +
//                ", file=" + file +
//                ", user=" + user +
                '}';

    }
}

