package controller;

import model.User;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    static UserServiceImpl userService = new UserServiceImpl();
    static int idUser = 0;

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
            case "homepage":
                showFormHomepage(request,response);
                break;
            default:
                showFormLogin(request , response);
        }
    }


    private void showFormLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/register.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showFormHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/homepage.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
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
            case "login":
                try {
                    loginUser(request , response , session);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

   public static void loginUser(HttpServletRequest request, HttpServletResponse response , HttpSession session) throws SQLException, IOException {
        String account = request.getParameter("account");
        String passWord = request.getParameter("passWord");
        User user = userService.findByNameAndPass(account , passWord);
        if(user == null){
            response.sendRedirect("user/login.jsp");
        } else {
            session.setAttribute("fullName" , user.getFullName());
            idUser = user.getId();
            response.sendRedirect("/users?action=homepage&idUser="+idUser);
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String account = request.getParameter("account");
        String passWord = request.getParameter("passWord");
        String fullName = request.getParameter("fullName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        userService.add(new User(account, passWord, fullName, dateOfBirth));

    }


}
