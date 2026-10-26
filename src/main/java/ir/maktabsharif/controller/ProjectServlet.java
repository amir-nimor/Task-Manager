package ir.maktabsharif.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktabsharif.dto.project.ProjectRequestDto;
import ir.maktabsharif.dto.project.ProjectResponseDto;
import ir.maktabsharif.service.help.Email;
import ir.maktabsharif.service.help.LogService;
import ir.maktabsharif.service.help.Sms;
import ir.maktabsharif.service.project.ProjectServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "projects",value = "/projects/*")
public class ProjectServlet extends HttpServlet {
    private ProjectServiceImpl projectService;
    private ObjectMapper objectMapper;
    private LogService logService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.projectService = (ProjectServiceImpl) getServletContext().getAttribute("projectService");
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
        this.logService = new LogService(new Sms());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getPathInfo();

        if ("/Add".equals(path)){
            req.getRequestDispatcher("/project/AddProject.jsp").forward(req,resp);
            return;
        }

        List<ProjectResponseDto> projectResponseDtos = projectService.findAll();
        req.setAttribute("projects",projectResponseDtos);
        logService.log("find All Project");
        req.getRequestDispatcher("/project/ALLProject.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProjectRequestDto projectRequestDto = objectMapper.readValue(req.getReader(),ProjectRequestDto.class);
        ProjectResponseDto projectResponseDto = projectService.save(projectRequestDto);
        logService.log("add new project "+projectResponseDto);
        objectMapper.writeValue(resp.getWriter(),projectResponseDto);


    }
}
