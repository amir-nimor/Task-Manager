package ir.maktabsharif.repository.task;

import ir.maktabsharif.dao.TaskDao;
import ir.maktabsharif.repository.base.BaseRepository;

import java.util.List;

public interface TaskRepository extends BaseRepository<TaskDao,Long> {
    List<TaskDao> findByAssignedId(Integer id);
}
