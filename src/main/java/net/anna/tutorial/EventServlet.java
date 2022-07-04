package net.anna.tutorial;



import event.controller.EventController;
import event.controller.FileController;
import event.controller.UserController;
import event.model.Event;
import event.model.File;
import event.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class EventServlet  extends HttpServlet {

    EventController eventController = new EventController();
    UserController userController = new UserController();
    FileController fileController = new FileController();

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
                List<Event> events = eventController.getAll();
                messageWriter.println(events);
                break;
            case "getbyid": //          default:
                String idS = request.getParameter("id");
                Long id = Long.parseLong(idS);
                Event event = eventController.getById(id);
                messageWriter.println(event);
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

                String action1 = request.getParameter("eventaction");

                String idS1 = request.getParameter("user_id");
                Long user_id = Long.parseLong(idS1);

                String idS2 = request.getParameter("file_id");
                Long file_id = Long.parseLong(idS2);

                User u =  userController.getById(user_id);
                File f = fileController.getById(file_id);

                Event event = eventController.update(id,action1,f,u);
                messageWriter.println(event);
                break;
            case "save":
                String action2 = request.getParameter("eventaction");

                String idS3 = request.getParameter("user_id");
                Long user_id3 = Long.parseLong(idS3);
                messageWriter.println("user_id3 = "+  user_id3);

                String idS4 = request.getParameter("file_id");
                Long file_id4 = Long.parseLong(idS4);
                messageWriter.println("file_id4 = "+  file_id4);

                User u1 =  userController.getById(user_id3);
                File f1 = fileController.getById(file_id4);

                Event event1 = eventController.save(action2,f1,u1);
                messageWriter.println(event1);
                break;
            case "delete":
                String idS5 = request.getParameter("id");
                Long id1 = Long.parseLong(idS5);
                Event event2 = eventController.getById(id1);
                if (event2==null) {
                    messageWriter.println("Event ( id = "+id1+" ) does not exist");
                }
                else{
                    fileController.deleteById(id1);
                    messageWriter.println("File ( id = "+event2.getId()+" ) deleted");
                }
                break;
            default:
                messageWriter.println("Error");
                break;
        }
    }
}