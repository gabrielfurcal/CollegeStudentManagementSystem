/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IClassroomRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Build;
import models.Classroom;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class ClassroomRepository extends BaseRepository<Classroom> implements IClassroomRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public ClassroomRepository()
    {
        super(Classroom.class);
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
    public List<Classroom> findClassrooms(Build build)
    {
        try
        {
            List<Classroom> classrooms = getEntityManager().createQuery("SELECT c FROM Classroom c WHERE c.build = :build", Classroom.class).setParameter("build", build).getResultList();
            
            if(build == null)
                throw new Exception("Build not found");
            
            return classrooms;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public Classroom findClassroom(BigDecimal classroomId)
    {
        try
        {
            Classroom classroom = getEntityManager().createQuery("SELECT c FROM Classroom c WHERE c.classroomsPK.classroomId = :classroomId", Classroom.class).setParameter("classroomId", classroomId).getSingleResult();
            
            return classroom;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}