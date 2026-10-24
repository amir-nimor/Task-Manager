package ir.maktabsharif.service.project;

import ir.maktabsharif.dto.project.ProjectRequestDto;
import ir.maktabsharif.dto.project.ProjectResponseDto;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;

import java.util.List;

public interface ProjectService {

    ProjectResponseDto save(ProjectRequestDto projectRequestDto);
    ProjectResponseDto update(Integer id , ProjectRequestDto projectRequestDto);
    Integer delete(Integer id);
    ProjectResponseDto findById(Integer id);
    List<ProjectResponseDto> findAll();
}
