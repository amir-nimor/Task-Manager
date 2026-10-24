package ir.maktabsharif.command;

import ir.maktabsharif.dao.ProjectDao;
import ir.maktabsharif.dto.project.ProjectRequestDto;
import ir.maktabsharif.dto.project.ProjectResponseDto;

public class ProjectCommand implements Commandable<ProjectDao,Integer, ProjectRequestDto, ProjectResponseDto>{
    @Override
    public ProjectDao requestToDao(ProjectRequestDto projectRequestDto) {
        ProjectDao projectDao = ProjectDao.builder()
                .name(projectRequestDto.getName())
                .description(projectRequestDto.getDescription())
                .build();
        projectDao.setId(projectRequestDto.getId());
        return projectDao;
    }

    @Override
    public ProjectResponseDto daoToResponse(ProjectDao projectDao) {
        ProjectResponseDto projectResponseDto = ProjectResponseDto.builder()
                .id(projectDao.getId())
                .description(projectDao.getDescription())
                .name(projectDao.getName())
                .build();
        return projectResponseDto;
    }
}
