/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IBaseRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream.Where;

/**
 *
 * @author Gabriel_Liberato
 * @param <T>
 */
public abstract class BaseRepository<T> implements IBaseRepository<T>
{
    private static final long serialVersionUID = 1L;
    
    private Class<T> entity;
    
    protected abstract EntityManager getEntityManager();
    
    protected abstract JinqJPAStreamProvider getJinqJPAStreamProvider();
    
    public BaseRepository()    
    {
        
    }
    
    public BaseRepository(Class<T> entityClass)
    {
        this.entity = entityClass;
    }
    
    @Override
    public T findById(Object key)
    {
        try
        {
            return getEntityManager().find(entity, key);
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public List<T> findAll()
    {
        try
        {
            getEntityManager().clear();
            return getEntityManager().createQuery("Select t from " + entity.getSimpleName() + " t").getResultList();
        }
        catch(Exception ex)
        {
            return new ArrayList<T>();
        }
    }

    @Override
    public List<T> getListBy(Where<T, Exception> predicate)
    {
        try
        {
            List<T> listEntities = getJinqJPAStreamProvider().streamAll(getEntityManager(), entity).where(predicate).toList();
            
            return listEntities;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
            return new ArrayList<T>();
        }
    }

    @Override
    public boolean save(T entity)
    {
        try
        {
            getEntityManager().persist(entity);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(T entity)
    {
        try
        {
            getEntityManager().merge(entity);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    @Override
    public boolean delete(T entity)
    {
        try
        {
            if(!getEntityManager().contains(entity))
                entity = getEntityManager().merge(entity);
            
            getEntityManager().remove(entity);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    @Override
    public boolean deleteById(Object keys)
    {
        try
        {
            T entityFromDb = getEntityManager().find(entity, keys);
            
            if(entityFromDb != null)
            {
                if(!getEntityManager().contains(entityFromDb))
                    entityFromDb = getEntityManager().merge(entityFromDb);
                
                getEntityManager().remove(entityFromDb);
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}