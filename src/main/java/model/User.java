package model;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private int id;
    private String account;
    private String password;
    private String fullName;
    private String img;
    private String dateOfBirth;
    List<User> friendList;
    public User() {
    }

    public User( String account, String password, String fullName, String dateOfBirth) {
        this.account = account;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public User(int id, String account, String fullName, String img, String dateOfBirth, List<User> friendList) {
        this.id = id;
        this.account = account;
        this.fullName = fullName;
        this.img = img;
        this.dateOfBirth = dateOfBirth;
        this.friendList = friendList;
    }

    public User(int id, String account, String password, String fullName, String img, String dateOfBirth) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.fullName = fullName;
        this.img = img;
        this.dateOfBirth = dateOfBirth;
    }

    public User(int id, String account, String password, String fullName, String dateOfBirth) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public User(int id, String acc, String fullName, String dateOfBirth) {
        this.id = id;
        this.account = acc;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<User> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<User> friendList) {
        this.friendList = friendList;
    }
}
