package ir.maktabsharif.repository.task;

import ir.maktabsharif.dao.BaseModel.BaseModel;
import ir.maktabsharif.dao.TaskDao;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.exception.HibernateException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;

import java.util.List;

public class TaskRepositoryImpl extends BaseRepositoryImpl<TaskDao, Long> implements TaskRepository {

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

    @Override
    public List<TaskDao> findByAssignedId(Integer id) {
        try {
            return HibernateUtil.read(em -> {
                return em.createQuery("FROM TaskDao t where t.userDao.id = ?1")
                        .setParameter(1, id)
                        .getResultList();
            });
        } catch (HibernateException e) {
            throw new RepositoryException("you is user not founded = " + e.getMessage());
        }
    }

}
