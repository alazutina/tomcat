package net.anna.tutorial;

import event.controller.UserController;
import event.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserServlet  extends HttpServlet {

    UserController userController = new UserController();
    User u;

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
                List<User> userList;// = new ArrayList<>();
                userList = userController.getAll();
                messageWriter.println(userList);
                break;
            case "getbyid":
                String idS = request.getParameter("id");
                Long id = Long.parseLong(idS);

                u=userController.getById(id);
                messageWriter.println(u);
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
                User u=userController.getById(id);
                User u1 = userController.update(u.getId(),name);
                messageWriter.println(u1);
                break;
            case "save":
                String name1 = request.getParameter("name");
                User user = new User();
                user.setName(name1);
                User user1 = userController.save(user.getName());
                messageWriter.println(user1);
                break;
            case "delete":          //  default:
                String idS1 = request.getParameter("id");
                Long id1 = Long.parseLong(idS1);
                User user2 = userController.getById(id1);
                if (user2==null) {
                    messageWriter.println("User ( id = "+id1+" ) does not exist");
                }
                else{
                    userController.deleteById(id1);
                    messageWriter.println("User "+user2.getName()+" deleted");
                }
                break;
            default:
                messageWriter.println("Error");
                break;

    }
}}