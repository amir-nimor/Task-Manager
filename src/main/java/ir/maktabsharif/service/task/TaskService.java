package ir.maktabsharif.service.task;

import ir.maktabsharif.dto.task.TaskRequestDto;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto save(TaskRequestDto taskRequestDto);
    TaskResponseDto update(Long id , TaskRequestDto taskRequestDto);
    Long delete(Long id);
    TaskResponseDto findById(Long id);
    List<TaskResponseDto> findAll();

    List<TaskResponseDto> findByAssignedId(Integer id);

}
