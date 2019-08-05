/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.ICourseSectionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Course;
import models.CourseSection;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class CourseSectionRepository extends BaseRepository<CourseSection> implements ICourseSectionRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public CourseSectionRepository()
    {
        super(CourseSection.class);
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
    public List<CourseSection> findCoursesSections(Course course)
    {
        try
        {
            List<CourseSection> coursesSections = getEntityManager().createQuery("SELECT cs FROM CourseSection cs WHERE cs.course = :course", CourseSection.class).setParameter("course", course).getResultList();
            
            if(coursesSections == null)
                throw new Exception("List is empty");
            
            return coursesSections;
                
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public CourseSection findCourseSection(int courseSectionId)
    {
        try
        {
            CourseSection courseSection = getEntityManager().createQuery("SELECT cs FROM CourseSection cs WHERE cs.coursesSectionsPK.courseSectionId = :courseSectionId", CourseSection.class).setParameter("courseSectionId", courseSectionId).getSingleResult();
            
            return courseSection;  
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}