package service.impl;

import model.User;
import service.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements Service<User> {
    List<User> users = new ArrayList<>();

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
    public void add(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into user(account,passWord,fullName,dateOfBirth) values (?,?,?,?)");) {
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullName());
            preparedStatement.setString(4, user.getDateOfBirth());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean edit(User user) {
        return false;
    }
}
