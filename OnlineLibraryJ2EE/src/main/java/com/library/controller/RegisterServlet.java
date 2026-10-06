package com.library.controller;

import com.library.dao.UserDAO;
import com.library.util.PasswordUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String email=req.getParameter("email"), password=req.getParameter("password");
        if(new UserDAO().register(email,PasswordUtil.hash(password))) res.sendRedirect("login.jsp");
        else {req.setAttribute("error","Registration failed. Email may already exist."); req.getRequestDispatcher("register.jsp").forward(req,res);}
    }
}
