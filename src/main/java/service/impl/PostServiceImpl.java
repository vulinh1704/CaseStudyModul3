package service.impl;

import model.Comment;
import model.Post;
import model.User;
import service.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements Service<Post> {
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
        List<Post> postList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from post order by id desc");) {
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
                List<Comment> commentList = findAllComment(idPost);
                User user = userService.findById(userId);
                postList.add(new Post(idPost, userId, idComment, timePost, likeCount, accessModifier, img, content, user , commentList));
            }
        } catch (SQLException e) {

        }
        return postList;
    }

    public int showLike(int idPost) {
        int likeCount = 0;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select likeCount from post where id = ?");) {
            preparedStatement.setInt(1, idPost);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                likeCount = rs.getInt("likeCount");
            }
        } catch (SQLException e) {

        }
        return likeCount;
    }

    public void addLike(int likeCount, int idPost) {
        likeCount = likeCount + 1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update post set likeCount = ? where id = ?");) {
            preparedStatement.setInt(1, likeCount);
            preparedStatement.setInt(2, idPost);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public List<Comment> findAllComment(int idPost) throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from comment where idPost = ? order by id desc");) {
            preparedStatement.setInt(1, idPost);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                idPost = rs.getInt("idPost");
                int idUser = rs.getInt("idUser");
                String timeComment = rs.getString("timeComment");
                String content = rs.getString("content");
                User userComment = findUserComment(idUser);
                commentList.add(new Comment(id, idPost, idUser, timeComment, content , userComment));
            }
        }
        return commentList;
    }

    public User findUserComment(int idUer){
        User user = new User();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");) {
            preparedStatement.setInt(1, idUer);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = idUer;
                String account = rs.getString("account");
                String fullName = rs.getString("fullName");
                String dateOfBirth = rs.getString("dateOfBirth");
                user = new User(id,account,fullName,dateOfBirth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
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
