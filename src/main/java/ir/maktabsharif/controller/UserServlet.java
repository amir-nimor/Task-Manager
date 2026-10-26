package ir.maktabsharif.controller;

import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.service.help.Email;
import ir.maktabsharif.service.help.LogService;
import ir.maktabsharif.service.user.UserServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "user",value = "/users")
public class UserServlet extends HttpServlet {

    private UserServiceImpl userService;
    private LogService logService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = (UserServiceImpl) getServletContext().getAttribute("userService");
        this.logService = new LogService(new Email());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserResponseDto> userResponseDtos = userService.findAll();
        req.setAttribute("users",userResponseDtos);
        req.getRequestDispatcher("/user/AllUsers.jsp").forward(req,resp);
        logService.log("All Users");
    }


}
