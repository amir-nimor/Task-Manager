package ir.maktabsharif.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktabsharif.dao.enums.Rols;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.service.help.Email;
import ir.maktabsharif.service.help.LogService;
import ir.maktabsharif.service.help.Sms;
import ir.maktabsharif.service.user.UserServiceImpl;
import ir.maktabsharif.util.TokenUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "Register",value = "/Register")
public class Rigester extends HttpServlet {

    private UserServiceImpl userService;
    private ObjectMapper objectMapper;
    private LogService logService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = (UserServiceImpl) getServletContext().getAttribute("userService");
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
        this.logService = new LogService(new Email());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("token","");
        resp.addCookie(cookie);
        logService.log("deleted cookie");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserRequestDto userRequestDto = objectMapper.readValue(req.getReader(), UserRequestDto.class);

        if (userRequestDto.getName().equals("admin") && userRequestDto.getEmail().equals("admin")){
            userRequestDto.setRols(Rols.ADMIN);
        }else {
            userRequestDto.setRols(Rols.USER);
        }

        session.setAttribute("rols",userRequestDto.getRols());

        UserResponseDto userResponseDto = userService.save(userRequestDto);

        logService.log("add user this "+userResponseDto);

        String token = TokenUtil.generateToken(userResponseDto.getEmail());

        Cookie cookie = new Cookie("token",token);
        resp.addCookie(cookie);

        objectMapper.writeValue(resp.getWriter(),userResponseDto);

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRequestDto userRequestDto = objectMapper.readValue(req.getReader(), UserRequestDto.class);
        HttpSession session = req.getSession();

        Cookie[] cookies = req.getCookies();

        String tokenEmail = null;

        for (Cookie c:cookies){
            if (c.getName().equals("token")){
                tokenEmail = c.getValue();
            }
        }



        String email = null;
        if (tokenEmail != null){
            email =TokenUtil.getEmailFormTokent(tokenEmail);
        }else {
            req.getRequestDispatcher("register/CreateUser.jsp").forward(req,resp);
            return;
        }

        UserResponseDto userResponseDto = null;
        if (email != null){
           userResponseDto = userService.findBuEmail(email);

        }else {
            req.getRequestDispatcher("register/CreateUser.jsp").forward(req,resp);
            return;
        }

        if (userResponseDto != null){
            session.setAttribute("userRegister",userResponseDto);
            session.setAttribute("rols",userResponseDto.getRols());
            logService.log("Login this user "+userResponseDto);
            objectMapper.writeValue(resp.getWriter(),userResponseDto);
        }else{
            req.getRequestDispatcher("register/CreateUser.jsp").forward(req,resp);
            return;
        }
    }




}
