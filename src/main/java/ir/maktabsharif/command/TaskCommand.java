package ir.maktabsharif.command;

import ir.maktabsharif.dao.TaskDao;
import ir.maktabsharif.dto.task.TaskRequestDto;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.repository.project.ProjectRepositoryImpl;
import ir.maktabsharif.repository.user.UserRepositoryImpl;

public class TaskCommand implements Commandable<TaskDao,Long, TaskRequestDto, TaskResponseDto>{

    private UserRepositoryImpl userRepository;
    private ProjectRepositoryImpl projectRepository;

    public TaskCommand(){
        this.userRepository = new UserRepositoryImpl();
        this.projectRepository = new ProjectRepositoryImpl();
    }

    @Override
    public TaskDao requestToDao(TaskRequestDto taskRequestDto) {
        TaskDao taskDao = TaskDao.builder()
                .userDao(userRepository.read(taskRequestDto.getUserId()))
                .title(taskRequestDto.getTitle())
                .projectDao(projectRepository.read(taskRequestDto.getProjectId()))
                .estimatedMinutes(taskRequestDto.getEstimatedMinutes())
                .build();
        taskDao.setId(taskRequestDto.getId());
        return taskDao;
    }

    @Override
    public TaskResponseDto daoToResponse(TaskDao taskDao) {
        TaskResponseDto taskResponseDto = TaskResponseDto.builder()
                .id(taskDao.getId())
                .userId(taskDao.getUserDao().getId())
                .projectId(taskDao.getProjectDao().getId())
                .estimatedMinutes(taskDao.getEstimatedMinutes())
                .title(taskDao.getTitle())
                .build();

        return taskResponseDto;
    }
}
