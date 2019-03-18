/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;
import java.util.List;
import org.jinq.orm.stream.JinqStream.Where;

/**
 *
 * @author Gabriel_Liberato
 * @param <T>
 */
public interface IBaseRepository<T> extends Serializable
{
    public T findById(Object key);
    public List<T> findAll();
    public List<T> getListBy(Where<T,Exception> predicate);
    public boolean save(T entity);
    public boolean update(T entity);
    public boolean delete(T entity);
    public boolean deleteById(Object keys);
}