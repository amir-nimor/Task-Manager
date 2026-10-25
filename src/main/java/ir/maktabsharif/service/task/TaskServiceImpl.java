package ir.maktabsharif.service.task;

import ir.maktabsharif.command.Commandable;
import ir.maktabsharif.command.TaskCommand;
import ir.maktabsharif.dao.TaskDao;
import ir.maktabsharif.dao.UserDao;
import ir.maktabsharif.dto.task.TaskRequestDto;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.exception.ServiceException;
import ir.maktabsharif.exception.ValidationException;
import ir.maktabsharif.repository.task.TaskRepository;
import ir.maktabsharif.repository.task.TaskRepositoryImpl;
import ir.maktabsharif.util.Ruls;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private TaskCommand commandable;


    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        this.commandable = new TaskCommand();
    }


    private void validation(TaskRequestDto taskRequestDto){
        Ruls.check(taskRequestDto.getTitle().isBlank(),"your title is empty");
        Ruls.check(taskRequestDto.getUserId() == null,"your user id is empty");
        Ruls.check(taskRequestDto.getEstimatedMinutes() == null,"your time is empty");
        Ruls.check(taskRequestDto.getProjectId() == null,"your project id is empty");
    }

    @Override
    public TaskResponseDto save(TaskRequestDto taskRequestDto) {
        try {
            validation(taskRequestDto);
            TaskDao taskDao = commandable.requestToDao(taskRequestDto);
            return commandable.daoToResponse(taskRepository.create(taskDao));
        }catch (ValidationException | RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public TaskResponseDto update(Long id, TaskRequestDto taskRequestDto) {
        try {
            validation(taskRequestDto);
            TaskDao taskDao = commandable.requestToDao(taskRequestDto);
            return commandable.daoToResponse(taskRepository.update(id,taskDao));
        }catch (ValidationException | RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public Long delete(Long id) {
        try {
            return taskRepository.delete(id);
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public TaskResponseDto findById(Long id) {
        try {
            return commandable.daoToResponse(taskRepository.read(id));
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public List<TaskResponseDto> findAll() {
        try {
            List<TaskDao> taskDaos =  taskRepository.findAll();
            List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
            for (TaskDao u : taskDaos){
                taskResponseDtos.add(commandable.daoToResponse(u));
            }
            return taskResponseDtos;
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public List<TaskResponseDto> findByAssignedId(Integer id) {
        try {
            List<TaskDao> taskDaos =  taskRepository.findByAssignedId(id);
            List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
            for (TaskDao u : taskDaos){
                taskResponseDtos.add(commandable.daoToResponse(u));
            }
            return taskResponseDtos;
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }


}
