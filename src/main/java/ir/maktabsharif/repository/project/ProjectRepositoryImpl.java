package ir.maktabsharif.repository.project;

import ir.maktabsharif.dao.ProjectDao;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;

public class ProjectRepositoryImpl extends BaseRepositoryImpl<ProjectDao,Integer> implements ProjectRepository {

    public ProjectRepositoryImpl() {
        super(ProjectDao.class);
    }

    @Override
    protected void updateEntity(ProjectDao dbEntity, ProjectDao newEntity) {
        dbEntity.setName(newEntity.getName());
        dbEntity.setDescription(newEntity.getDescription());
    }
}
