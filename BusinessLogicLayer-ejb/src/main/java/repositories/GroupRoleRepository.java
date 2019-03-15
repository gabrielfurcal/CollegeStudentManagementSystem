/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IGroupRoleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Group;
import models.GroupRole;
import models.Role;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class GroupRoleRepository extends BaseRepository<GroupRole> implements IGroupRoleRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;

    public GroupRoleRepository()
    {
        super(GroupRole.class);
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
    public List<GroupRole> findGroupsRoles(Group group)
    {
        try
        {
            List<GroupRole> groupsRoles = getEntityManager().createQuery("SELECT gr FROM GroupRole gr WHERE gr.group = :group", GroupRole.class).setParameter("group", group).getResultList();
            
            if(groupsRoles == null)
                throw new Exception("List is empty");
            
            return groupsRoles;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<GroupRole> findGroupsRoles(Role role)
    {
        try
        {
            List<GroupRole> groupsRoles = getEntityManager().createQuery("SELECT gr FROM GroupRole gr WHERE gr.role = :role", GroupRole.class).setParameter("role", role).getResultList();
            
            if(groupsRoles == null)
                throw new Exception("List is empty");
            
            return groupsRoles;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<GroupRole> findGroupsRoles(Group group, Role role)
    {
        try
        {
            List<GroupRole> groupsRoles = getEntityManager().createQuery("SELECT gr FROM GroupRole gr WHERE gr.group = :group AND gr.role = :role", GroupRole.class).setParameter("group", group).setParameter("role", role).getResultList();
            
            if(groupsRoles == null)
                throw new Exception("List is empty");
            
            return groupsRoles;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }
}