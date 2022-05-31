package controller.friend;

import controller.PersonalPageServlet;
import controller.UserServlet;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FriendRequestServlet", value = "/FriendRequestServlet")
public class FriendRequestServlet extends HttpServlet {
    public UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "addFriend":
                addFriendRequest(request , response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void addFriendRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idUser1 = UserServlet.idUser;
        int idUser2 = PersonalPageServlet.userSearch.getId();
        userService.addFriendRequest(idUser1 , idUser2);
        response.sendRedirect("/users?action=homepage&idUser="+ UserServlet.idUser);
    }
}
