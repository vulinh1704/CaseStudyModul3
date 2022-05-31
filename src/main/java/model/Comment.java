package model;

public class Comment {
    int id;
    int idPost;
    int idUser;
    String timeComment;
    String content;
    User userComment;

    public Comment() {
    }

    public Comment(int id, int idPost, int idUser, String timeComment, String content, User userComment) {
        this.id = id;
        this.idPost = idPost;
        this.idUser = idUser;
        this.timeComment = timeComment;
        this.content = content;
        this.userComment = userComment;
    }

    public Comment(int idPost, int idUser, String timeComment, String content) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.timeComment = timeComment;
        this.content = content;
    }

    public Comment(int id, int idPost, int idUser, String timeComment, String content) {
        this.id = id;
        this.idPost = idPost;
        this.idUser = idUser;
        this.timeComment = timeComment;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(String timeComment) {
        this.timeComment = timeComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUserComment() {
        return userComment;
    }

    public void setUserComment(User userComment) {
        this.userComment = userComment;
    }
}
