package com.library.filter;

import com.library.model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)res;
        String uri=request.getRequestURI();
        String ctx=request.getContextPath();
        boolean publicPage=uri.equals(ctx+"/") || uri.endsWith("login.jsp") ||
            uri.endsWith("/login") || uri.endsWith("/register") ||
            uri.endsWith("register.jsp") || uri.contains("/css/");
        User user=(User)request.getSession().getAttribute("user");
        if(!publicPage && user==null) { response.sendRedirect(ctx+"/login.jsp"); return; }
        chain.doFilter(req,res);
    }
}
