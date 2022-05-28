package service.impl;

import model.Post;
import service.Service;

import java.sql.SQLException;
import java.util.List;

public class PostService implements Service<Post> {

    @Override
    public void add(Post post) throws SQLException {

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
