package service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T>{
    void add(T t) throws SQLException;
    T findById(int id);
    List<T> findAll();
    boolean delete(int id);
    boolean edit(T t);
}
