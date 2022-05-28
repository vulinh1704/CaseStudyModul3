package model;

public class Post {
    private int id;
    private int userId;
    private int idComment;
    private int timePost;
    private int likeCount;
    private String accessModifier;
    private String img;
    private String content;

    public Post() {
    }

    public Post(int id, int userId, int idComment, int timePost, int likeCount, String accessModifier, String img, String content) {
        this.id = id;
        this.userId = userId;
        this.idComment = idComment;
        this.timePost = timePost;
        this.likeCount = likeCount;
        this.accessModifier = accessModifier;
        this.img = img;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getTimePost() {
        return timePost;
    }

    public void setTimePost(int timePost) {
        this.timePost = timePost;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getAccessModifier() {
        return accessModifier;
    }

    public void setAccessModifier(String accessModifier) {
        this.accessModifier = accessModifier;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
