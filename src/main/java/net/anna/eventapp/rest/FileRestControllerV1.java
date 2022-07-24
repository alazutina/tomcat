package net.anna.eventapp.rest;

import com.google.gson.Gson;
import net.anna.eventapp.dto.FileDto;
import net.anna.eventapp.dto.UserDto;
import net.anna.eventapp.model.FileEntity;
import net.anna.eventapp.model.UserEntity;
import net.anna.eventapp.service.FileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileRestControllerV1 extends HttpServlet {

    FileService fileService = new FileService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();

        if(pathInfo==null){
                List<FileEntity> files;
                files = fileService.getAll();
                for(FileEntity file1 : files){
                      FileDto fileDto1 = FileDto.fromEntity(file1);
                    messageWriter.println(new Gson().toJson(fileDto1));
                }}
        else{
                Long id = Long.valueOf(pathInfo.substring(1));
                FileEntity file = fileService.getById(id);
                FileDto fileDto = FileDto.fromEntity(file);
                messageWriter.println(new Gson().toJson(fileDto));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        Long id = Long.valueOf(pathInfo.substring(1));

        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();

                String path = request.getParameter("path");
                FileEntity file1 = fileService.getById(id);
                FileEntity file2 = fileService.update(file1.getId(),path);
                FileDto fileDto1 = FileDto.fromEntity(file2);
                messageWriter.println(new Gson().toJson(fileDto1));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

                response.setContentType("application/json");
                PrintWriter messageWriter = response.getWriter();

                String path1 = request.getParameter("path");
                FileEntity file3 = new FileEntity();
                file3.setPath(path1);
                FileEntity file4 = fileService.save(file3.getPath());
                FileDto fileDto2 = FileDto.fromEntity(file4);
                messageWriter.println(new Gson().toJson(fileDto2));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        Long id = Long.valueOf(pathInfo.substring(1));

        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();

                FileEntity file5 = fileService.getById(id);
                FileDto fileDto3 = FileDto.fromEntity(file5);
                messageWriter.println("File " + new Gson().toJson(fileDto3));
                if (file5==null) {
                    messageWriter.println(" does not exist");
                }
                else{
                    fileService.deleteById(id);
                    messageWriter.println(" deleted");
                }

    }
}