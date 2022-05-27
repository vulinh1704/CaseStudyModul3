package controller;

import model.User;
import service.Service;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "register":
                showFormRegister(request, response);
                break;
            default:
                showFormRegister(request, response);
        }
    }

    private void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("pitnik-MXH/register.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action) {
            case "register":
                try {
                    registerUser(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String account = request.getParameter("account");
        String passWord = request.getParameter("passWord");
        String fullName = request.getParameter("fullName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        userService.add(new User(account, passWord, fullName, dateOfBirth));
    }
}
