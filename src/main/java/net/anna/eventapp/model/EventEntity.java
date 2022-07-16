package net.anna.eventapp.model;



import javax.persistence.*;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private  Long id;
    @Column(name="ACTION")
    private  String action;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FILE", referencedColumnName = "ID")
//    @Column(name="ID_FILE")
    private FileEntity fileEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER", referencedColumnName = "id")
    private UserEntity userEntity;

    public EventEntity(){}

    public EventEntity(Long id, String action, FileEntity fileEntity, UserEntity userEntity){
        this.id = id;
        this.action = action;
        this.fileEntity = fileEntity;
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public FileEntity getFile() {
        return fileEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setFileEntity(FileEntity fileEntity) {
        this.fileEntity = fileEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"" +
                ",\"action\": \"" + action + "\"" +
                ",\"user_id\": \"" + userEntity.getId() + "\"" +
                ",\"file_id\": \"" + fileEntity.getId() + "\"" +
//                ", file=" + file +
//                ", user=" + user +
                '}';

    }
}
