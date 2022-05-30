package service.impl;

import model.Post;
import model.User;
import service.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements Service<Post> {
    List<Post> postList = new ArrayList<>();
    UserServiceImpl userService = new UserServiceImpl();
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/casestudy3?useSSL=false", "root", "1234");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void add(Post post) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into post(userId,timePost,img,content) values (?,?,?,?)");) {
            preparedStatement.setInt(1, post.getUserId());
            preparedStatement.setString(2, post.getTimePost());
            preparedStatement.setString(3, post.getImg());
            preparedStatement.setString(4, post.getContent());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Post findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Post> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from post ");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idPost = rs.getInt("id");
                int userId = rs.getInt("userId");
                int idComment = rs.getInt("idComment");
                String timePost = rs.getString("timePost");
                int likeCount = rs.getInt("likeCount");
                String accessModifier = rs.getString("accessModifier");
                String img = rs.getString("img");
                String content = rs.getString("content");
                User user = userService.findById(userId);
                postList.add(new Post(idPost, userId, idComment, timePost, likeCount, accessModifier, img, content , user));
            }
        } catch (SQLException e) {

        }
        return postList;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean edit(Post post) {
        return false;
    }
}
