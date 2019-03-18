/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.ITelephoneRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Telephone;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class TelephoneRepository extends BaseRepository<Telephone> implements ITelephoneRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public TelephoneRepository()
    {
        super(Telephone.class);
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
    public List<Telephone> findTelephones(String telephoneNumber)
    {
        try
        {
            List<Telephone> telephones = getEntityManager().createQuery("SELECT t FROM Telephone t WHERE t.telephoneNumber = :telephoneNumber", Telephone.class).setParameter("telephoneNumber", telephoneNumber).getResultList();
            
            return telephones;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public Telephone findTelephone(BigDecimal id, String telephoneNumber)
    {
        try
        {
            Telephone telephone = getEntityManager().createQuery("SELECT t FROM Telephone t WHERE t.telephoneNumber = :telephoneNumber AND t.telephoneId = :id", Telephone.class).setParameter("telephoneNumber", telephoneNumber).setParameter("id", id).getSingleResult();
            
            return telephone;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}