package ir.maktabsharif.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktabsharif.dto.project.ProjectResponseDto;
import ir.maktabsharif.dto.task.TaskRequestDto;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.service.help.Email;
import ir.maktabsharif.service.help.LogService;
import ir.maktabsharif.service.help.Sms;
import ir.maktabsharif.service.project.ProjectServiceImpl;
import ir.maktabsharif.service.task.TaskServiceImpl;
import ir.maktabsharif.service.user.UserServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Task", value = "/Task/*")
public class TaskServlet extends HttpServlet {

    private TaskServiceImpl taskService;
    private ProjectServiceImpl projectService;
    private UserServiceImpl userService;
    private ObjectMapper objectMapper;
    private LogService logService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.taskService = (TaskServiceImpl) getServletContext().getAttribute("taskService");
        this.userService = (UserServiceImpl) getServletContext().getAttribute("userService");
        this.projectService = (ProjectServiceImpl) getServletContext().getAttribute("projectService");
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
        this.logService = new LogService(new Sms());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();


        if ("/update".equals(path)) {
            Long id = Long.parseLong(req.getParameter("id"));
            TaskResponseDto taskResponseDto = taskService.findById(id);
            req.setAttribute("taskResponseDto",taskResponseDto);
            logService.log("go to update task");
            req.getRequestDispatcher("/page/UpdateTask.jsp").forward(req,resp);
            return;
        }
        if ("/delete".equals(path)) {
            logService.log("go to delete");
            doDelete(req,resp);
            return;
        }
        if ("/Add".equals(path)){
            List<ProjectResponseDto> projectResponseDtos = projectService.findAll();
            List<UserResponseDto> userResponseDtos = userService.findAll();
            req.setAttribute("users",userResponseDtos);
            req.setAttribute("projects",projectResponseDtos);
            logService.log("go to add");
            req.getRequestDispatcher("/page/AddTasks.jsp").forward(req,resp);
            return;
        }



        Integer id = null;
        if (req.getParameter("id")!= null){
              id = Integer.parseInt(req.getParameter("id"));
        }

        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();

        if (id != null){
            taskResponseDtos = taskService.findByAssignedId(id);
        }else {
            taskResponseDtos = taskService.findAll();
        }
        req.setAttribute("tasks", taskResponseDtos);

        req.getRequestDispatcher("./page/ShowTasks.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskRequestDto taskRequestDto = objectMapper.readValue(req.getReader(),TaskRequestDto.class);

        TaskResponseDto taskResponseDto = taskService.save(taskRequestDto);
        logService.log("save this "+taskResponseDto);
        HttpSession session = req.getSession(false);
        session.setAttribute("task",taskResponseDto);
        objectMapper.writeValue(resp.getWriter(),taskResponseDto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        TaskRequestDto taskRequestDto = objectMapper.readValue(req.getReader(),TaskRequestDto.class);

        TaskResponseDto taskResponseDto = taskService.update(taskRequestDto.getId(),taskRequestDto);
        logService.log("update this "+taskResponseDto);
        req.setAttribute("Task",taskResponseDto);
        session.setAttribute("taskResponseDto",taskResponseDto);
        objectMapper.writeValue(resp.getWriter(),taskResponseDto);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Long iddeleted = taskService.delete(id);
        logService.log("delete this id "+iddeleted);
        req.getRequestDispatcher("/sucsesfully/DeleteSucsesfully.jsp").forward(req, resp);
        return;
    }
}
