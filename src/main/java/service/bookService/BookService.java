package service.bookService;

import config.ConnectionSingleton;
import model.Book;
import model.CategoryBook;
import service.categoryService.CategoryService;
import service.categoryService.ICategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookService implements IBookService {
    public static final String INSERT_INTO_BOOKS_NAME_DESCRIPTION_PRICE_PRODUCER_ID_CATEGORY_VALUES = "insert into books (name, description, price, producer, id_category) values (?,?,?,?,?)";
    public static final String SELECT_FROM_BOOKS_WHERE_ID = "select * from books where id = ?";
    public static final String DELETE_FROM_BOOKS_WHERE_ID = "delete from books where id = ?";
    public static final String UPDATE_BOOKS_SET_ID_NAME_DESCRIPTION_PRICE_PRODUCER_ID_CATEGORY_WHERE_ID = "UPDATE books SET id = ?, name = ?, description = ?, price=?, producer=?, id_category=? WHERE id = ?";
    private final ICategory categoryService = new CategoryService();
    public static final String SELECT_FROM_BOOKS = "select * from books";
    //    private

    @Override
    public List<Book> showAll() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_BOOKS)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String producer = resultSet.getString("producer");
                int category_id = resultSet.getInt("id_category");
                CategoryBook categoryBook = categoryService.findById(category_id);
                bookList.add(new Book(id, name, description, price, producer, categoryBook));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;

    }


    @Override
    public void insert(Book book) throws SQLException {
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_BOOKS_NAME_DESCRIPTION_PRICE_PRODUCER_ID_CATEGORY_VALUES)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setString(4, book.getProducer());
            preparedStatement.setInt(5, book.getCategoryBook().getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_BOOKS_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id1 = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String producer = resultSet.getString("producer");
                int id_category = resultSet.getInt("id_category");
                CategoryBook categoryBook = categoryService.findById(id_category);
                book = new Book(id1, name, description, price, producer, categoryBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean update(Book book) throws SQLException {
        boolean rowUpdated = false;
        Connection connection = ConnectionSingleton.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKS_SET_ID_NAME_DESCRIPTION_PRICE_PRODUCER_ID_CATEGORY_WHERE_ID);
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getName());
        preparedStatement.setString(3, book.getDescription());
        preparedStatement.setDouble(4, book.getPrice());
        preparedStatement.setString(5, book.getProducer());
        preparedStatement.setInt(6, book.getCategoryBook().getId());
        preparedStatement.setInt(7, book.getId());
        rowUpdated = preparedStatement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted = false;
        Connection connection = ConnectionSingleton.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_BOOKS_WHERE_ID);
        preparedStatement.setInt(1, id);
        rowDeleted = preparedStatement.executeUpdate() > 0;
        return rowDeleted;
    }

//    public static void main(String[] args) throws SQLException {
//        BookService bookService = new BookService();
////        bookService.findById(4);
////        bookService.delete(3);
//        bookService.update(new Book(4,"Python","program3",20,"VUClone",new CategoryBook(2)));
////        bookService.insert(new Book("vu", "thai", 14, "van", new CategoryBook(1)));
//    }
}
