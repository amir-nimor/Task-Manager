package ir.maktabsharif.repository.user;

import ir.maktabsharif.dao.UserDao;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.repository.base.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<UserDao,Integer> {

    UserDao findBuEmail(String email);
}
