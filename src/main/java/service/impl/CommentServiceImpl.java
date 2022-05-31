package service.impl;

import model.Comment;
import model.Post;
import service.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements Service<Comment> {
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
    public void add(Comment comment) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into comment(idPost,idUser,timeComment,content) values (?,?,?,?)");) {
            preparedStatement.setInt(1, comment.getIdPost());
            preparedStatement.setInt(2, comment.getIdUser());
            preparedStatement.setString(3, comment.getTimeComment());
            preparedStatement.setString(4, comment.getContent());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Comment findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean edit(Comment comment) {
        return false;
    }
}
