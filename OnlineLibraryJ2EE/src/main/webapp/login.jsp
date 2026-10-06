<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html><head><title>Library Login</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="css/style.css"></head>
<body><div class="container" style="max-width:430px;margin-top:90px"><div class="card p-4"><h2 class="mb-3">Online Library</h2><p class="text-muted">J2EE Library Management System</p>
<% if(request.getAttribute("error")!=null){ %><div class="alert alert-danger"><%=request.getAttribute("error")%></div><% } %>
<form method="post" action="login"><input class="form-control mb-3" name="email" placeholder="Email" required><input class="form-control mb-3" type="password" name="password" placeholder="Password" required><button class="btn btn-primary w-100">Login</button></form>
<div class="text-center mt-3"><a href="register.jsp">Create student account</a></div></div></div></body></html>
