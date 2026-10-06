package com.library.controller;
import com.library.dao.BorrowDAO;
import com.library.model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        User u=(User)req.getSession().getAttribute("user");
        BorrowDAO dao=new BorrowDAO();
        String action=req.getParameter("action");
        if("approve".equals(action)) dao.approve(Integer.parseInt(req.getParameter("id")));
        else if("reject".equals(action)) dao.reject(Integer.parseInt(req.getParameter("id")));
        else if("return".equals(action)) dao.returnBook(Integer.parseInt(req.getParameter("id")));
        if("ADMIN".equals(u.getRole())) req.setAttribute("requests",dao.findAll());
        else req.setAttribute("requests",dao.findByUser(u.getUserId()));
        req.getRequestDispatcher("/borrow.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException{
        User u=(User)req.getSession().getAttribute("user");
        new BorrowDAO().create(u.getUserId(),Integer.parseInt(req.getParameter("bookId")));
        res.sendRedirect("borrow");
    }
}
