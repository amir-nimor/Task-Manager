package ir.maktabsharif.repository.user;

import ir.maktabsharif.dao.UserDao;
import ir.maktabsharif.dto.task.TaskResponseDto;
import ir.maktabsharif.exception.HibernateException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.repository.base.BaseRepositoryImpl;
import ir.maktabsharif.util.HibernateUtil;

import java.util.List;

public class UserRepositoryImpl extends BaseRepositoryImpl<UserDao, Integer> implements UserRepository {

    public UserRepositoryImpl() {
        super(UserDao.class);
    }

    @Override
    protected void updateEntity(UserDao dbEntity, UserDao newEntity) {
        dbEntity.setName(newEntity.getName());
        dbEntity.setEmail(newEntity.getEmail());
        dbEntity.setRols(newEntity.getRols());
    }


    @Override
    public UserDao findBuEmail(String email) {
        try {
            return HibernateUtil.read(em -> {
                return em.createQuery("from UserDao u where u.email = ?1", UserDao.class)
                        .setParameter(1, email)
                        .getSingleResult();
            });
        } catch (HibernateException e) {
            throw new RepositoryException("your email not founded => "+e.getMessage());
        }
    }
}
