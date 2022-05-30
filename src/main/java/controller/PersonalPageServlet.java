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
    static User userSearch = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = request.getParameter("account");
        try {
            userSearch = userService.findByName(account);
            session.setAttribute("fullNameSearch" , userSearch.getFullName());
            response.sendRedirect("page/personalPage.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
