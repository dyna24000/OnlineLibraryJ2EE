package com.library.controller;
import com.library.dao.BookDAO;
import com.library.model.Book;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String action=req.getParameter("action"); BookDAO dao=new BookDAO();
        if("delete".equals(action)) { dao.delete(Integer.parseInt(req.getParameter("id"))); res.sendRedirect("books"); return; }
        if("edit".equals(action)) req.setAttribute("book",dao.findById(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("books",dao.findAll());
        req.getRequestDispatcher("/books.jsp").forward(req,res);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException{
        Book b=new Book();
        b.setTitle(req.getParameter("title")); b.setAuthor(req.getParameter("author"));
        b.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        b.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        String id=req.getParameter("bookId");
        BookDAO dao=new BookDAO();
        if(id==null || id.isEmpty()) dao.save(b); else {b.setBookId(Integer.parseInt(id)); dao.update(b);}
        res.sendRedirect("books");
    }
}
