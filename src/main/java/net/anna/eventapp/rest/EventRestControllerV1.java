package net.anna.eventapp.rest;



import com.google.gson.Gson;
import net.anna.eventapp.dto.EventDto;
import net.anna.eventapp.dto.FileDto;
import net.anna.eventapp.model.EventEntity;
import net.anna.eventapp.model.FileEntity;
import net.anna.eventapp.model.UserEntity;
import net.anna.eventapp.service.EventService;
import net.anna.eventapp.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class EventRestControllerV1 extends HttpServlet {

    EventService eventService = new EventService();
    UserService userService = new UserService();
    FileService fileService = new FileService();

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
                List<EventEntity> events = eventService.getAll();
                for(EventEntity event : events) {
                    EventDto eventDto = EventDto.fromEntity(event);
                    messageWriter.println(new Gson().toJson(eventDto));
                }
                break;
            case "getbyid":
                String idS = request.getParameter("id");
                Long id = Long.parseLong(idS);
                EventEntity event1 = eventService.getById(id);
                EventDto eventDto1 = EventDto.fromEntity(event1);
                messageWriter.println(new Gson().toJson(eventDto1));
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

                UserEntity u =  userService.getById(user_id);
                FileEntity f = fileService.getById(file_id);

                EventEntity event = eventService.update(id,action1,f,u);
                EventDto eventDto2 = EventDto.fromEntity(event);
                messageWriter.println(new Gson().toJson(eventDto2));
                break;
            case "save":
                String action2 = request.getParameter("eventaction");

                String idS3 = request.getParameter("user_id");
                Long user_id3 = Long.parseLong(idS3);
                messageWriter.println("user_id3 = "+  user_id3);

                String idS4 = request.getParameter("file_id");
                Long file_id4 = Long.parseLong(idS4);
                messageWriter.println("file_id4 = "+  file_id4);

                UserEntity u1 =  userService.getById(user_id3);
                FileEntity f1 = fileService.getById(file_id4);

                EventEntity event1 = eventService.save(action2,f1,u1);
                EventDto eventDto4 = EventDto.fromEntity(event1);
                messageWriter.println(new Gson().toJson(eventDto4));
                break;
            case "delete":
                String idS5 = request.getParameter("id");
                Long id1 = Long.parseLong(idS5);
                EventEntity eventEntity = eventService.getById(id1);

                EventDto eventDto5 = EventDto.fromEntity(eventEntity);
                messageWriter.println("Event " + new Gson().toJson(eventDto5));
                if (eventEntity==null) {
                    messageWriter.println(" does not exist");
                }
                else{
                    fileService.deleteById(id1);
                    messageWriter.println("  deleted");
                }
                break;
            default:
                messageWriter.println("Error");
                break;
        }
    }
}