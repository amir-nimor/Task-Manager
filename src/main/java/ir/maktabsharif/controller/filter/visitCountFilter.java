package ir.maktabsharif.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class visitCountFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        Integer visitCount = (Integer) session.getAttribute("visitCount");

        if (visitCount == null){
            visitCount = 1;
        }else {
            visitCount++;
        }

        session.setAttribute("visitCount",visitCount);

        filterChain.doFilter(request,response);


    }
}
