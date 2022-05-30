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

@WebServlet("/PostServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PostServiceImpl postService = new PostServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("upload.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File("C:\\Users\\Linh\\IdeaProjects\\Module3\\BaiTap\\CaseStudy3\\src\\main\\resources\\uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
