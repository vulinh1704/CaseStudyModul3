package controller;

import model.Comment;
import service.impl.CommentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CommentServlet", urlPatterns = "/comments")
public class CommentServlet extends HttpServlet {
    CommentServiceImpl commentService = new CommentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action) {
            case "add":
                try {
                    add(request, response);
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idPost = Integer.parseInt(request.getParameter("idPost"));
        int idUser = UserServlet.idUser;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timeComment = time.format(fmt);
        String content = request.getParameter("contentComment");
        commentService.add(new Comment(idPost, idUser, timeComment, content));
        response.sendRedirect("/users?action=homepage&idUser="+ UserServlet.idUser);
    }
}
