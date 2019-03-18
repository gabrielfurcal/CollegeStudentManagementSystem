/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IStudentRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.CourseSectionHistorical;
import models.Student;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class StudentRepository extends BaseRepository<Student> implements IStudentRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public StudentRepository()
    {
        super(Student.class);
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
    public Student findStudent(String studentId)
    {
        try
        {
            Student student = getEntityManager().createQuery("select s from Student s where s.studentsPK.studentId =  :studentId", Student.class).setParameter("studentId", studentId).getSingleResult();
            
            return student;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public List<Student> findStudents(CourseSectionHistorical courseSectionHistorical)
    {
        try
        {
            List<Student> students = getEntityManager().createQuery("SELECT cp.student FROM ClassParticipant cp WHERE cp.courseSectHist := courseSectionHistorical", Student.class).setParameter("courseSectionHistorical", courseSectionHistorical).getResultList();
            
            if (students == null)
                throw new Exception("Students not found");
            
            return students;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }
}