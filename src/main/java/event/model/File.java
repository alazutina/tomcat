package event.model;


import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

//    File
//    int id
//    String path
//----------------------------------------------------
//    CREATE TABLE files( ID INT NOT NULL AUTO_INCREMENT,   PRIMARY KEY (ID),
//    PATH VARCHAR (100)     NOT NULL);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private  Long id;
    @Column(name="PATH")
    private  String path;

    public File(){
    }

    public File(Long id, String path){
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

