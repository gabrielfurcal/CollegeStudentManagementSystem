/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IUserTelephoneRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Telephone;
import models.User;
import models.UserTelephone;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class UserTelephoneRepository extends BaseRepository<UserTelephone> implements IUserTelephoneRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public UserTelephoneRepository()
    {
        super(UserTelephone.class);
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
    public List<UserTelephone> findUsersTelephones(User user)
    {
        try
        {
            return getEntityManager().createQuery("select ut from UserTelephone ut where ut.user = :user", UserTelephone.class).setParameter("user", user).getResultList();
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public UserTelephone findUserTelephone(User user, Telephone telephone)
    {
        try
        {
            return getEntityManager().createQuery("select ut from UserTelephone ut where ut.user = :user and ut.telephone = :telephone", UserTelephone.class).setParameter("telephone", telephone).setParameter("user", user).getSingleResult();
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public UserTelephone findUserTelephone(BigDecimal telephoneId)
    {
        try
        {
            return getEntityManager().createQuery("select ut from UserTelephone ut where ut.telephoneId.telephoneId = :telephoneId", UserTelephone.class).setParameter("telephoneId", telephoneId).getSingleResult();
        }
        catch(Exception ex)
        {
            return null;
        }
    }
}