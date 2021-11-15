package service;

import model.CategoryBook;

import java.sql.SQLException;
import java.util.List;

public interface IServiceAll<T> {
    public List<T> showAll();

    public void insert(T t) throws SQLException;

    public T findById(int id);

    public boolean update(T t) throws SQLException;

    public boolean delete(int id) throws SQLException;

}
