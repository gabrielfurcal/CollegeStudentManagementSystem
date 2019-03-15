/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IUserRoleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Role;
import models.User;
import models.UserRole;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class UserRoleRepository extends BaseRepository<UserRole> implements IUserRoleRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;

    public UserRoleRepository()
    {
        super(UserRole.class);
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
    public List<UserRole> findUsersRoles(User user)
    {
        try
        {
            List<UserRole> usersRoles = getEntityManager().createQuery("SELECT ur FROM UserRole ur WHERE ur.user = :user", UserRole.class).setParameter("user", user).getResultList();
            
            if(usersRoles == null)
                throw new Exception("List empty");
            
            return usersRoles;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<UserRole> findUsersRoles(User user, Role role)
    {
        try
        {
            List<UserRole> usersRoles = getEntityManager().createQuery("SELECT ur FROM UserRole ur WHERE ur.user = :user AND ur.role = :role", UserRole.class).setParameter("role", role).setParameter("user", user).getResultList();
            
            if(usersRoles == null)
                throw new Exception("List empty");
            
            return usersRoles;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }
}