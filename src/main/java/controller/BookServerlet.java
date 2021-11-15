package controller;

import model.Book;
import model.CategoryBook;
import service.bookService.BookService;
import service.bookService.IBookService;
import service.categoryService.CategoryService;
import service.categoryService.ICategory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = "/books")
public class BookServerlet extends HttpServlet {
    private static IBookService bookService = new BookService();
    private static ICategory categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                ShowEditForm(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            case "view":
                showViewBook(request, response);
                break;
            default:
                listBook(request, response);
                break;
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response) {
        List<Book> bookList = bookService.showAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/list.jsp");
        request.setAttribute("bookList", bookList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showViewBook(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/view.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            bookService.delete(id);
            response.sendRedirect("/books");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void ShowEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id_book = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.findById(id_book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/edit.jsp");
        request.setAttribute("book", book);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
        List<CategoryBook> categoryList = categoryService.showAll();
        request.setAttribute("category", categoryList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createBook(request, response);
                break;
            case "edit":
                editBook(request, response);
                break;
        }
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int category_id = Integer.parseInt(request.getParameter("category"));
        String producer = request.getParameter("producer");
        CategoryBook category = categoryService.findById(category_id);
        Book book = new Book(id, name, description, price, producer, category);
        try {
            bookService.update(book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("book/edit.jsp");
            request.setAttribute("book", book);
            List<CategoryBook> categoryBooks = categoryService.showAll();
            request.setAttribute("categoryBooks", categoryBooks);
            request.setAttribute("message", "Successfully edited!");
            dispatcher.forward(request, response);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    private void createBook(HttpServletRequest request, HttpServletResponse response) {
        List<CategoryBook> categoryBookList = categoryService.showAll();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String producer = request.getParameter("producer");
        int category_id = Integer.parseInt(request.getParameter("category"));
        CategoryBook category = categoryService.findById(category_id);
        Book book = new Book(name, description, price, producer, category);
        try {
            bookService.insert(book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
            request.setAttribute("category",categoryBookList);
            request.setAttribute("message", "Successful new creation!");
            dispatcher.forward(request,response);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
