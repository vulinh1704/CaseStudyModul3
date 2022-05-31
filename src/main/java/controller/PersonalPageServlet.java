package controller;

import model.User;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PersonalPageServlet", value = "/PersonalPageServlet")
public class PersonalPageServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    public static User userSearch = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            default:
                showAbout(request , response);
        }
    }

    private void showAbout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/personalPage.jsp");
        requestDispatcher.forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = request.getParameter("account");
        try {
            userSearch = userService.findByAccount(account);
            session.setAttribute("userSearch" , userSearch);
            response.sendRedirect("page/personalPage.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
