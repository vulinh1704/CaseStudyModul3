package controller;

import model.Post;
import service.impl.PostServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@WebServlet("/PostServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PostServiceImpl postService = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "like":
                countLike(request , response);
                break;
            default:
                request.getRequestDispatcher("upload.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        addPost(request ,response);
    }

    private void addPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");
        for (Part part : request.getParts()) {
            part.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
        }
        int idUser = UserServlet.idUser;
        String content = request.getParameter("content");
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String timePost = time.format(fmt);
        fileName = "uploads/" + fileName;
        Post post = new Post(idUser, timePost, fileName, content);
        try {
            postService.add(post);
            response.sendRedirect("/users?action=homepage&idUser=" + UserServlet.idUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void countLike(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int likeCount = postService.showLike(id);
        postService.addLike(likeCount, id);
        response.sendRedirect("/users?action=homepage&idUser=" + UserServlet.idUser);
    }

    public File getFolderUpload() {
        File folderUpload = new File("C:\\Users\\Linh\\IdeaProjects\\Module3\\BaiTap\\CaseStudy3\\src\\main\\resources\\uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
