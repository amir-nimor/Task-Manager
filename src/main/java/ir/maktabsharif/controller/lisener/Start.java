package ir.maktabsharif.controller.lisener;

import ir.maktabsharif.dao.enums.Rols;
import ir.maktabsharif.dto.project.ProjectRequestDto;
import ir.maktabsharif.dto.project.ProjectResponseDto;
import ir.maktabsharif.dto.task.TaskRequestDto;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.repository.project.ProjectRepositoryImpl;
import ir.maktabsharif.repository.task.TaskRepositoryImpl;
import ir.maktabsharif.repository.user.UserRepositoryImpl;
import ir.maktabsharif.service.project.ProjectServiceImpl;
import ir.maktabsharif.service.task.TaskServiceImpl;
import ir.maktabsharif.service.user.UserServiceImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Start implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=========================================");
        System.out.println("Server is Running");

        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
        TaskRepositoryImpl taskRepository = new TaskRepositoryImpl();


        UserServiceImpl userService = new UserServiceImpl(userRepository);
        TaskServiceImpl taskService = new TaskServiceImpl(taskRepository);
        ProjectServiceImpl projectService = new ProjectServiceImpl(projectRepository);


        UserRequestDto userRequestDto1 = UserRequestDto.builder().email("amir@gamli").name("amir").rols(Rols.USER).build();
        UserRequestDto userRequestDto2 = UserRequestDto.builder().email("ali@gamli").name("ali").rols(Rols.USER).build();
        UserRequestDto userRequestDto3 = UserRequestDto.builder().email("reza@gamli").name("reza").rols(Rols.USER).build();
        UserRequestDto userRequestDto4 = UserRequestDto.builder().email("john@gamli").name("john").rols(Rols.USER).build();

        ProjectRequestDto projectRequestDto1 = ProjectRequestDto.builder().name("java").description("project a hw 32").build();
        ProjectRequestDto projectRequestDto2 = ProjectRequestDto.builder().name("python").description("project a hw 32").build();
        ProjectRequestDto projectRequestDto3 = ProjectRequestDto.builder().name("c").description("project a hw 32").build();


        UserResponseDto userResponseDto1 = userService.save(userRequestDto1);
        UserResponseDto userResponseDto2 = userService.save(userRequestDto2);
        UserResponseDto userResponseDto3 = userService.save(userRequestDto3);
        UserResponseDto userResponseDto4 = userService.save(userRequestDto4);


        ProjectResponseDto projectResponseDto1 = projectService.save(projectRequestDto1);
        ProjectResponseDto projectResponseDto2 = projectService.save(projectRequestDto2);
        ProjectResponseDto projectResponseDto3 = projectService.save(projectRequestDto3);


        TaskRequestDto taskRequestDto1 = TaskRequestDto.builder().title("task 1").userId(userResponseDto1.getId()).projectId(projectResponseDto1.getId()).build();
        TaskRequestDto taskRequestDto2 = TaskRequestDto.builder().title("task 2").userId(userResponseDto2.getId()).projectId(projectResponseDto2.getId()).build();
        TaskRequestDto taskRequestDto3 = TaskRequestDto.builder().title("task 3").userId(userResponseDto3.getId()).projectId(projectResponseDto2.getId()).build();
        TaskRequestDto taskRequestDto4 = TaskRequestDto.builder().title("task 4").userId(userResponseDto4.getId()).projectId(projectResponseDto3.getId()).build();
        TaskRequestDto taskRequestDto5 = TaskRequestDto.builder().title("task 5").userId(userResponseDto3.getId()).projectId(projectResponseDto1.getId()).build();
        TaskRequestDto taskRequestDto6 = TaskRequestDto.builder().title("task 6").userId(userResponseDto3.getId()).projectId(projectResponseDto1.getId()).build();
        TaskRequestDto taskRequestDto7 = TaskRequestDto.builder().title("task 7").userId(userResponseDto1.getId()).projectId(projectResponseDto3.getId()).build();

        taskService.save(taskRequestDto1);
        taskService.save(taskRequestDto2);
        taskService.save(taskRequestDto3);
        taskService.save(taskRequestDto4);
        taskService.save(taskRequestDto5);
        taskService.save(taskRequestDto6);
        taskService.save(taskRequestDto7);


        sce.getServletContext().setAttribute("userService",userService);
        sce.getServletContext().setAttribute("projectService",projectService);
        sce.getServletContext().setAttribute("taskService",taskService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("==============================================");
        System.out.println("server is down");
    }
}
