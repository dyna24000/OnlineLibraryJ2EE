package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.*;

public class BookDAO {
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.*, c.category_name FROM books b LEFT JOIN categories c ON b.category_id=c.category_id ORDER BY b.book_id DESC";
        try (Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql);
             ResultSet rs=ps.executeQuery()) {
            while(rs.next()) {
                Book b=new Book();
                b.setBookId(rs.getInt("book_id")); b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author")); b.setQuantity(rs.getInt("quantity"));
                b.setCategoryId(rs.getInt("category_id")); b.setCategoryName(rs.getString("category_name"));
                list.add(b);
            }
        } catch(SQLException e){e.printStackTrace();}
        return list;
    }

    public Book findById(int id) {
        String sql="SELECT * FROM books WHERE book_id=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id); ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Book b=new Book(); b.setBookId(id); b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author")); b.setQuantity(rs.getInt("quantity"));
                b.setCategoryId(rs.getInt("category_id")); return b;
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

    public void save(Book b) {
        String sql="INSERT INTO books(title,author,quantity,category_id) VALUES(?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,b.getTitle()); ps.setString(2,b.getAuthor()); ps.setInt(3,b.getQuantity());
            ps.setInt(4,b.getCategoryId()); ps.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    public void update(Book b) {
        String sql="UPDATE books SET title=?,author=?,quantity=?,category_id=? WHERE book_id=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,b.getTitle()); ps.setString(2,b.getAuthor()); ps.setInt(3,b.getQuantity());
            ps.setInt(4,b.getCategoryId()); ps.setInt(5,b.getBookId()); ps.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    public void delete(int id) {
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement("DELETE FROM books WHERE book_id=?")){
            ps.setInt(1,id); ps.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }
}
