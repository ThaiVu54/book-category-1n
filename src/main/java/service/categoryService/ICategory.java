package service.categoryService;

import model.CategoryBook;
import service.IServiceAll;

import java.sql.SQLException;
import java.util.List;

public interface ICategory extends IServiceAll<CategoryBook> {
    @Override
    List<CategoryBook> showAll();

    @Override
    void insert(CategoryBook categoryBook) throws SQLException;

    @Override
    CategoryBook findById(int id);

    @Override
    boolean delete(int id) throws SQLException;

    boolean update(CategoryBook categoryBook) throws SQLException;
}
