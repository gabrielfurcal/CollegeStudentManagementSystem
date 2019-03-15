/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.ICourseSectionHistoricalRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.CourseSection;
import models.CourseSectionHistorical;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class CourseSectionHistoricalRepository extends BaseRepository<CourseSectionHistorical> implements ICourseSectionHistoricalRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public CourseSectionHistoricalRepository()
    {
        super(CourseSectionHistorical.class);
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
    public List<CourseSectionHistorical> findCoursesSectionsHistorical(CourseSection courseSection)
    {
        try
        {
            List<CourseSectionHistorical> courseSectionHistorical = getEntityManager().createQuery("SELECT csh FROM CourseSectionHistorical csh WHERE csh.courseSection = :courseSection ORDER BY csh.courseSectHistCreationDate DESC", CourseSectionHistorical.class).setParameter("courseSection", courseSection).getResultList();
            
            return courseSectionHistorical;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }
}
