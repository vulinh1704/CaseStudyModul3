package service.impl;

import model.User;
import service.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements Service<User> {
    public static int countRequest = 0;
    public static int countFriend = 0;

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
             PreparedStatement preparedStatement = connection.prepareStatement("select id,account,passWord,fullName,dateOfBirth from user where id = ?");) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String account = rs.getString("account");
                String passWord = rs.getString("passWord");
                String fullName = rs.getString("fullName");
                String dateOfBirth = rs.getString("dateOfBirth");
                user = new User(id, account, passWord, fullName, dateOfBirth);
            }
        }
        return user;
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
             PreparedStatement preparedStatement = connection.prepareStatement("select id,account,passWord,fullName,dateOfBirth from user where account like ? and passWord like ?");) {
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, passWord);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String acc = rs.getString("account");
                String pass = rs.getString("passWord");
                String fullName = rs.getString("fullName");
                String dateOfBirth = rs.getString("dateOfBirth");
                user = new User(id, acc, pass, fullName, dateOfBirth);
            }
        }
        return user;
    }

    public User findByAccount(String account) throws SQLException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id,account,passWord,fullName,dateOfBirth from user where account like ?");) {
            preparedStatement.setString(1, account);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String acc = rs.getString("account");
                String fullName = rs.getString("fullName");
                String dateOfBirth = rs.getString("dateOfBirth");
                user = new User(id, acc, fullName, dateOfBirth);
            }
        }
        return user;
    }

    public void addFriendRequest(int idUser1, int idUser2) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into friendship(idUser1,idUser2) values (?,?)");) {
            preparedStatement.setInt(1, idUser1);
            preparedStatement.setInt(2, idUser2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> requestList(int idUser2) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from friendship where idUser2 = ? and status = 0");) {
            preparedStatement.setInt(1, idUser2);
            ResultSet rs = preparedStatement.executeQuery();
            int id = 0;
            countRequest = 0;
            while (rs.next()) {
                countRequest++;
                id = rs.getInt("id");
                idUser2 = rs.getInt("idUser2");
                userList.add(findById(idUser2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> friendList(int idUser) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from friendship where (idUser1 = ? or idUser2 = ?) and status = 1");) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idUser);
            ResultSet rs = preparedStatement.executeQuery();
            int id = 0;
            countFriend = 0;
            while (rs.next()) {
                countFriend++;
                id = rs.getInt("id");
                int id1 = rs.getInt("idUser1");
                int id2 = rs.getInt("idUser2");
                userList.add(findById((idUser == id1) ? id1 : id2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void addFriend(int idUser2) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update friendship set status = 1 where idUser1 = ? or idUser2 = ?");) {
            preparedStatement.setInt(1, idUser2);
            preparedStatement.setInt(2, idUser2);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
