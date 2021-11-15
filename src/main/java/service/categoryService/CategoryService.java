package service.categoryService;

import config.ConnectionSingleton;
import model.CategoryBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategory {
//    private CategoryBook categoryBook = new CategoryBook();

    public static final String SELECT_FROM_CATEGORY = "select * from category";
    public static final String INSERT_INTO_CATEGORY_ID_NAME_VALUES = "insert into category (name) values (?)";
    public static final String SELECT_ID_NAME_FROM_CATEGORY_WHERE_ID = "select id, name from category where id = ?";
    public static final String UPDATE_BOOK_CATEGORY_SET_NAME_WHERE_ID = "UPDATE book.category SET name = ? WHERE id = ?";
    public static final String DELETE_FROM_CATEGORY_WHERE_ID = "DELETE FROM category WHERE id = ?";

    @Override
    public List<CategoryBook> showAll() {
        List<CategoryBook> categoryBookList = new ArrayList<>();
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_CATEGORY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categoryBookList.add(new CategoryBook(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryBookList;
    }


    @Override
    public void insert(CategoryBook categoryBook) throws SQLException {
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_CATEGORY_ID_NAME_VALUES)
        ) {
            preparedStatement.setString(1, categoryBook.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public CategoryBook findById(int id) {
        CategoryBook categoryBook = null;
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_NAME_FROM_CATEGORY_WHERE_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                categoryBook = new CategoryBook(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryBook;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_CATEGORY_WHERE_ID)) {
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(CategoryBook categoryBook) throws SQLException {
        boolean rowupdated = false;
        try (Connection connection = ConnectionSingleton.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_CATEGORY_SET_NAME_WHERE_ID)) {
            preparedStatement.setString(1, categoryBook.getName());
            preparedStatement.setInt(2, categoryBook.getId());
            rowupdated = preparedStatement.executeUpdate() > 0;
        }
        return rowupdated;
    }

//    public static void main(String[] args) throws SQLException {
//        CategoryService categoryService = new CategoryService();
//categoryService.delete(5)  ;  }
}