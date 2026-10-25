package ir.maktabsharif.controller;

import ir.maktabsharif.dto.task.TaskRequestDto;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.service.task.TaskServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Task", value = "/Task/*")
public class TaskServlet extends HttpServlet {

    private TaskServiceImpl taskService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.taskService = (TaskServiceImpl) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();


        if ("/update".equals(path)) {
            Long id = Long.parseLong(req.getParameter("id"));
            TaskResponseDto taskResponseDto = taskService.findById(id);
            req.setAttribute("taskResponseDto",taskResponseDto);
            req.getRequestDispatcher("/page/UpdateTask.jsp").forward(req,resp);
            return;
        }
        if ("/delete".equals(path)) {
            Long id = Long.parseLong(req.getParameter("id"));
            Long iddeleted = taskService.delete(id);
            req.getRequestDispatcher("/sucsesfully/DeleteSucsesfully.jsp").forward(req, resp);
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
        Long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        Integer estimatedMinutes =Integer.parseInt(req.getParameter("estimatedMinutes"));
        Integer projectId =Integer.parseInt(req.getParameter("projectId"));
        Integer userId =Integer.parseInt(req.getParameter("userId"));

        TaskRequestDto taskRequestDto = TaskRequestDto.builder()
                .estimatedMinutes(estimatedMinutes)
                .title(title)
                .userId(userId)
                .projectId(projectId)
                .id(id)
                .build();

        TaskResponseDto taskResponseDto = taskService.update(id,taskRequestDto);
        req.setAttribute("Task",taskResponseDto);
        req.getRequestDispatcher("/sucsesfully/UpdateSucsesfully.jsp").forward(req,resp);
    }


}
