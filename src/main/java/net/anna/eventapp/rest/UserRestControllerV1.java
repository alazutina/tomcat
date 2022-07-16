package net.anna.eventapp.rest;

import com.google.gson.Gson;
import net.anna.eventapp.dto.FileDto;
import net.anna.eventapp.dto.UserDto;
import net.anna.eventapp.model.UserEntity;
import net.anna.eventapp.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserRestControllerV1 extends HttpServlet {

    UserService userService = new UserService();
    UserEntity u;

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
                List<UserEntity> userList;
                userList = userService.getAll();
                messageWriter.println(userList);
                for(UserEntity user: userList){
                    UserDto userDto = UserDto.fromEntity(user);
                    messageWriter.println(new Gson().toJson(userDto));
                }
                break;
            case "getbyid":
                String idS = request.getParameter("id");
                Long id = Long.parseLong(idS);
                u = userService.getById(id);
                UserDto userDto = UserDto.fromEntity(u);
                messageWriter.println(new Gson().toJson(userDto));
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
                String name = request.getParameter("name");
                UserEntity u5=userService.getById(id);
                UserEntity u1 = userService.update(u5.getId(),name);
                UserDto userDto1 = UserDto.fromEntity(u1);
                messageWriter.println(new Gson().toJson(userDto1));
                break;
            case "save":
                String name1 = request.getParameter("name");
                UserEntity user = new UserEntity();
                user.setName(name1);
                UserEntity user1 = userService.save(user.getName());
                UserDto userDto2 = UserDto.fromEntity(user1);
                messageWriter.println(new Gson().toJson(userDto2));
                break;
            case "delete":          //  default:
                String idS1 = request.getParameter("id");
                Long id1 = Long.parseLong(idS1);
                UserEntity user2 = userService.getById(id1);
                UserDto userDto3 = UserDto.fromEntity(user2);
                messageWriter.println("User "+new Gson().toJson(userDto3));
                if (user2==null) {
                    messageWriter.println(" does not exist");
                }
                else{
                    userService.deleteById(id1);
                    messageWriter.println(" deleted");
                }
                break;
            default:
                messageWriter.println("Error");
                break;

    }
}
}