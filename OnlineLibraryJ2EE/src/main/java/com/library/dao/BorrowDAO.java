package com.library.dao;

import com.library.model.BorrowRequest;
import com.library.util.DBConnection;
import java.sql.*;
import java.util.*;

public class BorrowDAO {
    public boolean create(int userId, int bookId) {
        String sql="INSERT INTO borrow_requests(user_id,book_id,status,request_date) VALUES(?,?, 'PENDING', CURDATE())";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,userId); ps.setInt(2,bookId); return ps.executeUpdate()==1;
        }catch(SQLException e){return false;}
    }

    public List<BorrowRequest> findAll() {
        return query("SELECT r.*,u.email,b.title FROM borrow_requests r JOIN users u ON r.user_id=u.user_id JOIN books b ON r.book_id=b.book_id ORDER BY r.request_id DESC",0);
    }

    public List<BorrowRequest> findByUser(int userId) {
        return query("SELECT r.*,u.email,b.title FROM borrow_requests r JOIN users u ON r.user_id=u.user_id JOIN books b ON r.book_id=b.book_id WHERE r.user_id=? ORDER BY r.request_id DESC",userId);
    }

    private List<BorrowRequest> query(String sql,int userId) {
        List<BorrowRequest> list=new ArrayList<>();
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            if(userId>0) ps.setInt(1,userId);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                BorrowRequest r=new BorrowRequest();
                r.setRequestId(rs.getInt("request_id")); r.setUserId(rs.getInt("user_id"));
                r.setBookId(rs.getInt("book_id")); r.setStudentEmail(rs.getString("email"));
                r.setBookTitle(rs.getString("title")); r.setStatus(rs.getString("status"));
                r.setRequestDate(rs.getDate("request_date")); r.setDueDate(rs.getDate("due_date"));
                list.add(r);
            }
        }catch(SQLException e){e.printStackTrace();}
        return list;
    }

    public boolean approve(int requestId) {
        String update =
            "UPDATE borrow_requests r " +
            "JOIN books b ON r.book_id = b.book_id " +
            "SET r.status='APPROVED', " +
            "r.due_date=DATE_ADD(CURDATE(), INTERVAL 14 DAY), " +
            "b.quantity=b.quantity-1 " +
            "WHERE r.request_id=? " +
            "AND r.status='PENDING' " +
            "AND b.quantity>0";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(update)) {

            ps.setInt(1, requestId);

            int rows = ps.executeUpdate();

            System.out.println("APPROVE request ID: " + requestId);
            System.out.println("Rows updated: " + rows);

            return rows == 1;

        } catch (SQLException e) {
            System.out.println("APPROVE ERROR:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean reject(int requestId) {
        String sql="UPDATE borrow_requests SET status='REJECTED' WHERE request_id=? AND status='PENDING'";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,requestId); return ps.executeUpdate()==1;
        }catch(SQLException e){return false;}
    }

    public boolean returnBook(int requestId) {
        String sql =
            "UPDATE borrow_requests r " +
            "JOIN books b ON r.book_id = b.book_id " +
            "SET r.status='RETURNED', " +
            "b.quantity=b.quantity+1 " +
            "WHERE r.request_id=? " +
            "AND r.status='APPROVED'";

        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, requestId);

            int rows = ps.executeUpdate();

            System.out.println("RETURN request ID: " + requestId);
            System.out.println("Rows updated: " + rows);

            return rows == 1;

        } catch (SQLException e) {
            System.out.println("RETURN ERROR:");
            e.printStackTrace();
            return false;
        }
    }
}
