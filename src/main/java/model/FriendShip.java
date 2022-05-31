package model;

public class FriendShip {
    private int id;
    private int idUser1;
    private int idUser2;
    private int status;

    public FriendShip(int id, int idUser1, int idUser2, int status) {
        this.id = id;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
