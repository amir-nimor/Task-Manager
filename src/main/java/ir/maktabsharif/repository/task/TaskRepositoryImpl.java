package ir.maktabsharif.repository.task;

import ir.maktabsharif.dao.BaseModel.BaseModel;
import ir.maktabsharif.dao.TaskDao;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;

public class TaskRepositoryImpl extends BaseRepositoryImpl<TaskDao,Long> implements TaskRepository {

    public TaskRepositoryImpl() {
        super(TaskDao.class);
    }

    @Override
    protected void updateEntity(TaskDao dbEntity, TaskDao newEntity) {
        dbEntity.setTitle(newEntity.getTitle());
        dbEntity.setProjectDao(newEntity.getProjectDao());
        dbEntity.setUserDao(newEntity.getUserDao());
        dbEntity.setEstimatedMinutes(newEntity.getEstimatedMinutes());
    }
}
