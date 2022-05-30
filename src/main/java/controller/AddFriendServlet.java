package controller;

import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddFriendServlet", value = "/AddFriendServlet")
public class AddFriendServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addFriend":
                addFriend(request , response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void addFriend(HttpServletRequest request, HttpServletResponse response) {
        int idUser1 = UserServlet.idUser;
        int idUser2 = PersonalPageServlet.userSearch.getId();
        userService.addFriends(idUser1 , idUser2);
    }
}
