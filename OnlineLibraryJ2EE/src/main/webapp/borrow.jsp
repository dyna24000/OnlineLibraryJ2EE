<%@ page import="java.util.*,com.library.model.*,com.library.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Borrow Requests</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><nav class="navbar navbar-dark bg-dark"><div class="container"><a class="navbar-brand" href="dashboard">Online Library</a><a class="btn btn-outline-light btn-sm" href="logout">Logout</a></div></nav>
<div class="container py-4"><h2>Borrow Requests</h2><table class="table table-bordered bg-white"><tr><th>ID</th><th>Student</th><th>Book</th><th>Status</th><th>Request Date</th><th>Due Date</th><th>Action</th></tr>
<% User u=(User)session.getAttribute("user"); for(BorrowRequest r:(List<BorrowRequest>)request.getAttribute("requests")){ %>
<tr><td><%=r.getRequestId()%></td><td><%=r.getStudentEmail()%></td><td><%=r.getBookTitle()%></td><td><%=r.getStatus()%></td><td><%=r.getRequestDate()%></td><td><%=r.getDueDate()==null?"-":r.getDueDate()%></td><td>
<% if("ADMIN".equals(u.getRole()) && "PENDING".equals(r.getStatus())){ %><a class="btn btn-sm btn-success" href="borrow?action=approve&id=<%=r.getRequestId()%>">Approve</a> <a class="btn btn-sm btn-danger" href="borrow?action=reject&id=<%=r.getRequestId()%>">Reject</a><% } else if("STUDENT".equals(u.getRole()) && "APPROVED".equals(r.getStatus())){ %><a class="btn btn-sm btn-warning" href="borrow?action=return&id=<%=r.getRequestId()%>">Return Book</a><% } %>
</td></tr><% } %></table></div></body></html>
