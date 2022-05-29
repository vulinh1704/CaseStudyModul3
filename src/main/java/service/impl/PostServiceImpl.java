package service.impl;

import model.Post;
import service.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PostServiceImpl implements Service<Post> {
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
            preparedStatement.setInt(1, post.getId());
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
        return null;
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
