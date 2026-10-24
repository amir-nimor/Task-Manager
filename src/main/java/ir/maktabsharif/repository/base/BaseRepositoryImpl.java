package ir.maktabsharif.repository.base;


import ir.maktabsharif.dao.BaseModel.BaseModel;
import ir.maktabsharif.exception.EntityNotFounded;
import ir.maktabsharif.exception.HibernateException;
import ir.maktabsharif.exception.RepositoryException;
import ir.maktabsharif.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T extends BaseModel<ID>, ID extends Number> implements BaseRepository<T, ID> {

    private Class<T> entity;

    public BaseRepositoryImpl(Class<T> entity) {
        this.entity = entity;
    }

    protected abstract void updateEntity(T dbEntity,T newEntity);

    @Override
    public T create(T t) {
        try {
            return HibernateUtil.InTxReturn(em -> {
                em.persist(t);
                return t;
            });
        } catch (HibernateException e) {
            throw new RepositoryException("your operation create is faield => " + e.getMessage());
        }
    }

    @Override
    public T update(ID id,T t) {
        try {
            return HibernateUtil.InTxReturn(em -> {
                T databaseEntity = read(id);
                if (databaseEntity == null){
                    throw new EntityNotFounded("your entity not founded");
                }
                T dbEntity = em.find(entity,id);
                updateEntity(dbEntity,t);
                return t;
            });
        } catch (HibernateException | EntityNotFounded e) {
            throw new RepositoryException("your operation update is faield => " + e.getMessage());
        }
    }

    @Override
    public ID delete(ID id) {
        try {
            return HibernateUtil.InTxReturn(em -> {
                if (Optional.ofNullable(em.find(entity, id)).isPresent()) {
                    em.remove(em.find(entity, id));
                    return id;
                }
                throw new HibernateException("your entity Not Founded");
            });
        } catch (HibernateException e) {
            throw new RepositoryException("your operation delete is faield => " + e.getMessage());
        }
    }

    @Override
    public T read(ID id) {
        try {
            return HibernateUtil.read(em -> {
               return em.find(entity,id);
            });
        } catch (HibernateException e) {
            throw new RepositoryException("your operation findById is faield => " + e.getMessage());
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return HibernateUtil.read(em -> {
                return em.createQuery("from "+entity.getSimpleName())
                        .getResultList();
            });
        } catch (HibernateException e) {
            throw new RepositoryException("your operation findall is faield => " + e.getMessage());
        }
    }
}
