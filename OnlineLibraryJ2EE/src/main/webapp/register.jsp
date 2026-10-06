<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Register</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
<body><div class="container" style="max-width:430px;margin-top:90px"><div class="card p-4"><h2>Student Registration</h2>
<% if(request.getAttribute("error")!=null){ %><div class="alert alert-danger"><%=request.getAttribute("error")%></div><% } %>
<form method="post" action="register"><input class="form-control mb-3" name="email" type="email" placeholder="Email" required><input class="form-control mb-3" name="password" type="password" placeholder="Password" required><button class="btn btn-success w-100">Register</button></form>
<a class="d-block text-center mt-3" href="login.jsp">Back to login</a></div></div></body></html>
