package controller.friend;

import controller.UserServlet;
import model.User;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RespondFriendServlet", value = "/RespondFriendServlet")
public class RespondFriendServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "addF":
                int id = Integer.parseInt(request.getParameter("id"));
                userService.addFriend(UserServlet.idUser , id);
                response.sendRedirect("/users?action=homepage&idUser="+ UserServlet.idUser);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
