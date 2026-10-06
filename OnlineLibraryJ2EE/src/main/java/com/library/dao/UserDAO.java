package com.library.dao;

import com.library.model.User;
import com.library.util.DBConnection;
import java.sql.*;

public class UserDAO {
    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new User(rs.getInt("user_id"), rs.getString("email"),
                    rs.getString("password"), rs.getString("role"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean register(String email, String password) {
        String sql = "INSERT INTO users(email,password,role) VALUES(?,?, 'STUDENT')";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) { return false; }
    }
}
