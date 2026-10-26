package ir.maktabsharif.controller.filter;

import ir.maktabsharif.dao.enums.Rols;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/projects/*","/Task/update","/Task/delete","/Task/Add"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        if (session == null){
            response.sendError(404);
            return;
        }


        Rols rols = (Rols) session.getAttribute("rols");

        if (rols == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"your session not founded ");
            return;
        }

        if (rols.equals(Rols.ADMIN)){
            filterChain.doFilter(request,response);
            return;
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND,"your rols is not admin ");


    }
}
