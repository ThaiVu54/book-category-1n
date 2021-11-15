package service.bookService;

import model.Book;
import service.IServiceAll;

import java.sql.SQLException;
import java.util.List;

public interface IBookService extends IServiceAll<Book> {
    @Override
    List<Book> showAll();

    @Override
    void insert(Book book) throws SQLException;

    @Override
    Book findById(int id);

    @Override
    boolean delete(int id) throws SQLException;
}
