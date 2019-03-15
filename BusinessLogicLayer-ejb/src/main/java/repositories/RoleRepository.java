/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IRoleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class RoleRepository extends BaseRepository<Role> implements IRoleRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;
    
    public RoleRepository()
    {
        super(Role.class);
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
    public List<Role> findRoles(Group group)
    {
        try
        {
            List<GroupRole> groupsRoles = getEntityManager().createQuery("SELECT gr FROM GroupRole gr WHERE gr.group = :group", GroupRole.class).setParameter("group", group).getResultList();
            
            List<Role> roles = groupsRoles.stream().map(GroupRole::getRole).collect(Collectors.toList());
            
            if(roles == null)
                throw new Exception("Roles not found");
            
            return roles;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }
}