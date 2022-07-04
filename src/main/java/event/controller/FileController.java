package event.controller;

import event.model.File;
import event.repository.FileRepository;
import event.repository.hibernate.HibernateFile;

import java.util.List;

public class FileController {

    private final FileRepository fileRepository;

    public FileController() {
        this.fileRepository = new HibernateFile();
    }

    public FileController(FileRepository fileRepository) {

        this.fileRepository = fileRepository;
    }

    public File save (String path){
        File file = new File();
        file.setPath(path);
        File file1 = fileRepository.save(file);
        return file1;
    }

    public List<File> getAll(){
        return fileRepository.getAll();
    }


    public File getById(Long id){
        return fileRepository.getById(id);
    }

    public void deleteById(Long id) { fileRepository.deleteById(id); }

    public File update(Long id, String path) {
        File file = new File();
        file.setId(id);
        file.setPath(path);
        return fileRepository.update(file);
    }
}
