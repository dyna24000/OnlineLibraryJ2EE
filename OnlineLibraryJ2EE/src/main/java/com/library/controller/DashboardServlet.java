package com.library.controller;
import com.library.dao.BookDAO;
import com.library.model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setAttribute("books",new BookDAO().findAll());
        req.getRequestDispatcher("/dashboard.jsp").forward(req,res);
    }
}
