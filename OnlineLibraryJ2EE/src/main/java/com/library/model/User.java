package com.library.model;

public class User {
    private int userId;
    private String email;
    private String password;
    private String role;

    public User() {}
    public User(int userId, String email, String password, String role) {
        this.userId = userId; this.email = email; this.password = password; this.role = role;
    }
    public int getUserId() { return userId; }
    public void setUserId(int v) { userId = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { email = v; }
    public String getPassword() { return password; }
    public void setPassword(String v) { password = v; }
    public String getRole() { return role; }
    public void setRole(String v) { role = v; }
}
