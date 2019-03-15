/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IRolePermissionRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Permission;
import models.Role;
import models.RolePermission;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class RolePermissionRepository extends BaseRepository<RolePermission> implements IRolePermissionRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;

    public RolePermissionRepository()
    {
        super(RolePermission.class);
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
    public List<RolePermission> findRolesPermissions(Role role)
    {
        try
        {
            List<RolePermission> rolesPermissions = getEntityManager().createQuery("SELECT rp FROM RolePermission rp WHERE rp.role = :role", RolePermission.class).setParameter("role", role).getResultList();
            
            if(rolesPermissions == null)
                throw new Exception("List is empty");
            
            return rolesPermissions;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }   

    @Override
    public List<RolePermission> findRolesPermissions(Permission permission)
    {
        try
        {
            List<RolePermission> rolesPermissions = getEntityManager().createQuery("SELECT rp FROM RolePermission rp WHERE rp.permission = :permission", RolePermission.class).setParameter("permission", permission).getResultList();
            
            if(rolesPermissions == null)
                throw new Exception("List is empty");
            
            return rolesPermissions;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public List<RolePermission> findRolesPermissions(Role role, Permission permission)
    {
        try
        {
            List<RolePermission> rolesPermissions = getEntityManager().createQuery("SELECT rp FROM RolePermission rp WHERE rp.role = :role AND rp.permission = :permission", RolePermission.class).setParameter("role", role).setParameter("permission", permission).getResultList();
            
            if(rolesPermissions == null)
                throw new Exception("List is empty");
            
            return rolesPermissions;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }
}