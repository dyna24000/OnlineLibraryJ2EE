<%@ page import="java.util.*,com.library.model.*,com.library.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Dashboard</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="css/style.css"></head>
<body><nav class="navbar navbar-dark bg-dark"><div class="container"><a class="navbar-brand" href="dashboard">Online Library</a><div><span class="text-white me-3"><%=((User)session.getAttribute("user")).getEmail()%></span><a class="btn btn-outline-light btn-sm" href="logout">Logout</a></div></div></nav>
<div class="container hero"><h1>Library Dashboard</h1><p class="text-muted">Search the catalog and manage borrowing requests.</p>
<% User u=(User)session.getAttribute("user"); %>
<div class="mb-4"><% if("ADMIN".equals(u.getRole())){ %><a class="btn btn-primary me-2" href="books">Manage Books</a><a class="btn btn-warning" href="borrow">Borrow Requests</a><% } else { %><a class="btn btn-success" href="borrow">My Borrow History</a><% } %></div>
<div class="row"><% for(Book b:(List<Book>)request.getAttribute("books")){ %><div class="col-md-6 col-lg-3 mb-3"><div class="card p-3 h-100"><h5><%=b.getTitle()%></h5><p class="mb-1">By <%=b.getAuthor()%></p><p>Available: <b><%=b.getQuantity()%></b></p>
<% if("STUDENT".equals(u.getRole()) && b.getQuantity()>0){ %><form method="post" action="borrow"><input type="hidden" name="bookId" value="<%=b.getBookId()%>"><button class="btn btn-success btn-sm">Request Borrow</button></form><% } %>
</div></div><% } %></div></div></body></html>
