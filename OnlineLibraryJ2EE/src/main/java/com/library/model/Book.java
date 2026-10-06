package com.library.model;

public class Book {
    private int bookId, quantity, categoryId;
    private String title, author, categoryName;

    public int getBookId() { return bookId; }
    public void setBookId(int v) { bookId = v; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int v) { quantity = v; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int v) { categoryId = v; }
    public String getTitle() { return title; }
    public void setTitle(String v) { title = v; }
    public String getAuthor() { return author; }
    public void setAuthor(String v) { author = v; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String v) { categoryName = v; }
}
