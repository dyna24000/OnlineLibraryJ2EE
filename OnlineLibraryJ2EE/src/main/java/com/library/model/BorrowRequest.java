package com.library.model;

import java.sql.Date;

public class BorrowRequest {
    private int requestId, userId, bookId;
    private String studentEmail, bookTitle, status;
    private Date requestDate, dueDate;

    public int getRequestId() { return requestId; }
    public void setRequestId(int v) { requestId = v; }
    public int getUserId() { return userId; }
    public void setUserId(int v) { userId = v; }
    public int getBookId() { return bookId; }
    public void setBookId(int v) { bookId = v; }
    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String v) { studentEmail = v; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String v) { bookTitle = v; }
    public String getStatus() { return status; }
    public void setStatus(String v) { status = v; }
    public Date getRequestDate() { return requestDate; }
    public void setRequestDate(Date v) { requestDate = v; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date v) { dueDate = v; }
}
