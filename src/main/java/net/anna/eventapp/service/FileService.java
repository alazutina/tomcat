package net.anna.eventapp.service;

import net.anna.eventapp.repository.FileRepository;
import net.anna.eventapp.repository.hibernate.HibernateFile;
import net.anna.eventapp.model.FileEntity;

import java.util.List;

public class FileService {

    private final FileRepository fileRepository;

    public FileService() {
        this.fileRepository = new HibernateFile();
    }

    public FileService(FileRepository fileRepository) {

        this.fileRepository = fileRepository;
    }

    public FileEntity save (String path){
        FileEntity file = new FileEntity();
        file.setPath(path);
        FileEntity file1 = fileRepository.save(file);
        return file1;
    }

    public List<FileEntity> getAll(){
        return fileRepository.getAll();
    }


    public FileEntity getById(Long id){
        return fileRepository.getById(id);
    }

    public void deleteById(Long id) { fileRepository.deleteById(id); }

    public FileEntity update(Long id, String path) {
        FileEntity file = new FileEntity();
        file.setId(id);
        file.setPath(path);
        return fileRepository.update(file);
    }
}
