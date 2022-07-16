package net.anna.eventapp.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private  Long id;
    @Column(name="PATH")
    private  String path;

    public FileEntity(){
    }

    public FileEntity(Long id, String path){
        this.id=id;
        this.path=path;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"" +
                ",\"path\": \"" + path + "\"" +
                '}';
    }
}
