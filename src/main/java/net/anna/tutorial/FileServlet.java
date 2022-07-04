package net.anna.tutorial;

import event.controller.FileController;
import event.model.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileServlet extends HttpServlet {

    FileController  fileController = new FileController();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getParameter("action");
        System.out.println("Enter doGet" +action);


        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();
        switch (action == null ? "info" : action) {
            case "getall":
                List<File> files;
                files = fileController.getAll();
                messageWriter.println(files);
                break;
            case "getbyid": //          default:
                String idS = request.getParameter("id");
                Long id = Long.parseLong(idS);
                File file = fileController.getById(id);
                messageWriter.println(file);
                break;
            default:
                messageWriter.println("Error");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Enter doGet" +action);

        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();
        switch (action == null ? "info" : action) {
            case "update":
                String idS = request.getParameter("id");
                Long id = Long.parseLong(idS);
                String path = request.getParameter("path");
                File file1 = fileController.getById(id);
                File file2 = fileController.update(file1.getId(),path);
                messageWriter.println(file2);
                break;
            case "save":
                String path1 = request.getParameter("path");
                File file3 = new File();
                file3.setPath(path1);
                File file4 = fileController.save(file3.getPath());
                messageWriter.println(file4);
                break;
            case "delete":
                String idS1 = request.getParameter("id");
                Long id1 = Long.parseLong(idS1);
                File file5 = fileController.getById(id1);
                if (file5==null) {
                    messageWriter.println("File ( id = "+id1+" ) does not exist");
                }
                else{
                    fileController.deleteById(id1);
                    messageWriter.println("File "+file5.getPath()+" deleted");
                }
                break;
            default:
                messageWriter.println("Error");
                break;

        }
    }
}