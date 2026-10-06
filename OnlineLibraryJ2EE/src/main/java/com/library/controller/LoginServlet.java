package com.library.controller;

import com.library.dao.UserDAO;
import com.library.model.User;
import com.library.util.PasswordUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String email=req.getParameter("email"), password=req.getParameter("password");
        User u=new UserDAO().login(email, PasswordUtil.hash(password));
        if(u!=null){ req.getSession().setAttribute("user",u); res.sendRedirect(req.getContextPath()+"/dashboard"); }
        else { req.setAttribute("error","Invalid email or password."); req.getRequestDispatcher("/login.jsp").forward(req,res); }
    }
}
