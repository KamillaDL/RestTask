package com.rest.repositories;

import com.rest.managers.DBConnector;
import com.rest.models.Book;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepo implements IBookRepository {
    @Override
    public void create(Book book) {
        if (book != null) {
            Connection connection = null;
            try {
                connection = DBConnector.getConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String sql = "insert into books(name, author) values(?,?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = new ArrayList<Book>();
        ResultSet rs = null;
        if (name != null) {
            Connection connection = null;
            try {
                connection = DBConnector.getConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String sql = "select * from books where name = ?";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    books.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return books;
    }
}

