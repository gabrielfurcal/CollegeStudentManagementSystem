/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IClassParticipantRepository;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.ClassParticipant;
import models.CourseSectionHistorical;
import models.Student;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class ClassParticipantRepository extends BaseRepository<ClassParticipant> implements IClassParticipantRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public ClassParticipantRepository()
    {
        super(ClassParticipant.class);
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
    public ClassParticipant findClassParticipant(CourseSectionHistorical courseSectionHistorical, Student student)
    {
        try
        {
            ClassParticipant classParticipant = getEntityManager().createQuery("SELECT cp FROM ClassParticipant cp WHERE cp.student = :student AND cp.courseSectHist = :courseSectionHistorical", ClassParticipant.class).setParameter("student", student).setParameter("courseSectionHistorical", courseSectionHistorical).getSingleResult();

            return classParticipant;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}