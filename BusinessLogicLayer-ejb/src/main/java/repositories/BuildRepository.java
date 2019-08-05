/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IBuildRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Build;
import models.Campus;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class BuildRepository extends BaseRepository<Build> implements IBuildRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public BuildRepository()
    {
        super(Build.class);
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
    public List<Build> findBuilds(Campus campus)
    {
        try
        {
            List<Build> builds = getEntityManager().createQuery("SELECT b FROM Build b WHERE b.campus = :campus", Build.class).setParameter("campus", campus).getResultList();
            
            if(builds == null)
                throw new Exception("Builds not found");
            
            return builds;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public Build findBuild(int buildId)
    {
        try
        {
            Build build = getEntityManager().createQuery("SELECT b FROM Build b WHERE b.buildsPK.buildId = :buildId", Build.class).setParameter("buildId", buildId).getSingleResult();
            
            if(build == null)
                throw new Exception("Build not found");
            
            return build;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}