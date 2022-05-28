package service.impl;

import model.User;
import service.Service;

import java.sql.*;
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
    public User findById(int id) throws SQLException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select account,passWord,fullName,dateOfBirth from user where id = ?");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String account = rs.getString("account");
                String passWord = rs.getString("passWord");
                String fullName = rs.getString("fullName");
                String dateOfBirth = rs.getString("dateOfBirth");
                user = new User(account, passWord, fullName, dateOfBirth);
            }
        }
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

    public User findByNameAndPass(String account, String passWord) throws SQLException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select account,passWord,fullName,dateOfBirth from user where account like ? and passWord like ?");) {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, passWord);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String acc = rs.getString("account");
                String pass = rs.getString("passWord");
                String fullName = rs.getString("fullName");
                String dateOfBirth = rs.getString("dateOfBirth");
                user = new User(acc, pass, fullName, dateOfBirth);
            }
        }
        return user;
    }
}
