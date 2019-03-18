/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.ITeacherRepository;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Teacher;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class TeacherRepository extends BaseRepository<Teacher> implements ITeacherRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public TeacherRepository()
    {
        super(Teacher.class);
    }
    
    @PostConstruct
    protected void init()
    {
        JinqJPAStreamProviderGenerator jinqGenerator = JinqJPAStreamProviderGenerator.getInstance(em);
        streams = jinqGenerator.getJinqJPAStreamProvider();
    }
    
    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    @Override
    protected JinqJPAStreamProvider getJinqJPAStreamProvider()
    {
        return streams;
    }

    @Override
    public Teacher findTeacher(BigDecimal teacherId)
    {
        try
        {
            Teacher teacher = getEntityManager().createQuery("select t from Teacher t where t.teachersPK.teacherId = :teacherId", Teacher.class).setParameter("teacherId", teacherId).getSingleResult();
            
            return teacher;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}
