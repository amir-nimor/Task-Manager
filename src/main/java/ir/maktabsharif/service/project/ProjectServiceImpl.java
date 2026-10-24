package ir.maktabsharif.service.project;

import ir.maktabsharif.command.ProjectCommand;
import ir.maktabsharif.dao.ProjectDao;
import ir.maktabsharif.dao.UserDao;
import ir.maktabsharif.dto.project.ProjectRequestDto;
import ir.maktabsharif.dto.project.ProjectResponseDto;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.exception.ServiceException;
import ir.maktabsharif.exception.ValidationException;
import ir.maktabsharif.repository.project.ProjectRepository;
import ir.maktabsharif.repository.project.ProjectRepositoryImpl;
import ir.maktabsharif.util.Ruls;

import java.util.ArrayList;
import java.util.List;

public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;
    private ProjectCommand commandable;


    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
        this.commandable = new ProjectCommand();
    }


    private void validation(ProjectRequestDto ProjectRequestDto){
        Ruls.check(ProjectRequestDto.getDescription().isBlank(),"your description is empty");
        Ruls.check(ProjectRequestDto.getName().isBlank(),"your name is empty");
    }

    @Override
    public ProjectResponseDto save(ProjectRequestDto projectRequestDto) {
        try {
            validation(projectRequestDto);
            ProjectDao projectDao = commandable.requestToDao(projectRequestDto);
            return commandable.daoToResponse(projectRepository.create(projectDao));
        }catch (ValidationException | RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public ProjectResponseDto update(Integer id, ProjectRequestDto projectRequestDto) {
        try {
            validation(projectRequestDto);
            ProjectDao projectDao = commandable.requestToDao(projectRequestDto);
            return commandable.daoToResponse(projectRepository.update(id,projectDao));
        }catch (ValidationException | RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public Integer delete(Integer id) {
        try {
            return projectRepository.delete(id);
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public ProjectResponseDto findById(Integer id) {
        try {
            return commandable.daoToResponse(projectRepository.read(id));
        }catch (RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }

    @Override
    public List<ProjectResponseDto> findAll() {
        try {
            List<ProjectDao> projectDaos =  projectRepository.findAll();
            List<ProjectResponseDto> projectResponseDtos  = new ArrayList<>();
            for (ProjectDao p : projectDaos){
                projectResponseDtos.add(commandable.daoToResponse(p));
            }
            return projectResponseDtos;
        }catch (ValidationException | RepositoryException e){
            throw new ServiceException("your operation is failed => "+e.getMessage());
        }
    }
}
