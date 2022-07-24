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

        String pathInfo = request.getPathInfo();
        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();

        if(pathInfo==null){
                List<UserEntity> userList;
                userList = userService.getAll();
                for(UserEntity user: userList){
                    UserDto userDto = UserDto.fromEntity(user);
                    messageWriter.println(new Gson().toJson(userDto));
                }
        }
        else
        {
               Long id = Long.valueOf(pathInfo.substring(1));
               u = userService.getById(id);
               UserDto userDto = UserDto.fromEntity(u);
               messageWriter.println(new Gson().toJson(userDto));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        Long id = Long.valueOf(pathInfo.substring(1));

        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();

                String name = request.getParameter("name");
                UserEntity u5=userService.getById(id);
                UserEntity u1 = userService.update(u5.getId(),name);
                UserDto userDto1 = UserDto.fromEntity(u1);
                messageWriter.println(new Gson().toJson(userDto1));
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();

                String name1 = request.getParameter("name");
                UserEntity user = new UserEntity();
                user.setName(name1);
                UserEntity user1 = userService.save(user.getName());
                UserDto userDto2 = UserDto.fromEntity(user1);
                messageWriter.println(new Gson().toJson(userDto2));
           }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        Long id = Long.valueOf(pathInfo.substring(1));

        response.setContentType("application/json");
        PrintWriter messageWriter = response.getWriter();

                UserEntity user2 = userService.getById(id);
                UserDto userDto3 = UserDto.fromEntity(user2);
                messageWriter.println("User "+new Gson().toJson(userDto3));
                if (user2==null) {
                    messageWriter.println(" does not exist");
                }
                else{
                    userService.deleteById(id);
                    messageWriter.println(" deleted");
                }
    }

}
