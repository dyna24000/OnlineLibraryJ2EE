<%@ page import="java.util.*,com.library.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Books</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><nav class="navbar navbar-dark bg-dark"><div class="container"><a class="navbar-brand" href="dashboard">Online Library</a><a class="btn btn-outline-light btn-sm" href="logout">Logout</a></div></nav>
<div class="container py-4"><h2>Book Management</h2>
<% Book edit=(Book)request.getAttribute("book"); %>
<div class="card p-3 mb-4"><form method="post" action="books" class="row g-2">
<input type="hidden" name="bookId" value="<%=edit==null?"":edit.getBookId()%>">
<div class="col-md-3"><input class="form-control" name="title" placeholder="Title" value="<%=edit==null?"":edit.getTitle()%>" required></div>
<div class="col-md-3"><input class="form-control" name="author" placeholder="Author" value="<%=edit==null?"":edit.getAuthor()%>" required></div>
<div class="col-md-2"><input class="form-control" type="number" name="quantity" placeholder="Quantity" value="<%=edit==null?0:edit.getQuantity()%>" min="0" required></div>
<div class="col-md-2"><input class="form-control" type="number" name="categoryId" placeholder="Category ID" value="<%=edit==null?1:edit.getCategoryId()%>" min="1" required></div>
<div class="col-md-2"><button class="btn btn-primary w-100"><%=edit==null?"Add Book":"Update Book"%></button></div></form></div>
<table class="table table-bordered"><tr><th>ID</th><th>Title</th><th>Author</th><th>Quantity</th><th>Category</th><th>Action</th></tr>
<% for(Book b:(List<Book>)request.getAttribute("books")){ %><tr><td><%=b.getBookId()%></td><td><%=b.getTitle()%></td><td><%=b.getAuthor()%></td><td><%=b.getQuantity()%></td><td><%=b.getCategoryName()%></td><td><a class="btn btn-sm btn-warning" href="books?action=edit&id=<%=b.getBookId()%>">Edit</a> <a class="btn btn-sm btn-danger" href="books?action=delete&id=<%=b.getBookId()%>" onclick="return confirm('Delete this book?')">Delete</a></td></tr><% } %></table></div></body></html>
