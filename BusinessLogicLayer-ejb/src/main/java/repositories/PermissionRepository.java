/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import interfaces.IPermissionRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jinq.JinqJPAStreamProviderGenerator;
import models.Permission;
import models.Role;
import models.User;
import models.RolePermission;
import models.UserRole;
import org.jinq.jpa.JinqJPAStreamProvider;

/**
 *
 * @author Gabriel_Liberato
 */
@Stateless
public class PermissionRepository extends BaseRepository<Permission> implements IPermissionRepository
{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName="CsmsPU")
    private EntityManager em;
    
    private JinqJPAStreamProvider streams;

    public PermissionRepository()
    {
        super(Permission.class);
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
    public List<Permission> findPermissions(Role role)
    {
        try
        {
            List<RolePermission> rolesPermissions = getEntityManager().createQuery("SELECT rp FROM RolePermission rp WHERE rp.role = :role", RolePermission.class).setParameter("role", role).getResultList();
            
            List<Permission> permissions = rolesPermissions.stream().map(RolePermission::getPermission).collect(Collectors.toList());
            
            if(permissions == null)
                throw new Exception("Permissions not found");
            
            return permissions;
        }
        catch(Exception ex)
        {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean hasUserPermission(int userId, String url)
    {
        User user = getEntityManager().find(User.class, userId);
            
        List<Role> userRoles = getEntityManager().createQuery("SELECT ur FROM UserRole ur WHERE ur.user = :user", UserRole.class).setParameter("user", user).getResultList().stream().map(UserRole::getRole).collect(Collectors.toList());
            
        List<Permission> userPermissions = new ArrayList<>();

        userRoles.forEach(r -> {
            getEntityManager().createQuery("SELECT rp FROM RolePermission rp WHERE rp.role = :role", RolePermission.class).setParameter("role", r).getResultList().stream().map(RolePermission::getPermission).collect(Collectors.toList()).forEach(p -> userPermissions.add(p));
        });

        boolean hasPermission = false;

        for (Permission userPermission : userPermissions)
        {
            if(userPermission.getPermissionUrl().toUpperCase().equals(url.toUpperCase()))
            {
                hasPermission = true;
                break;
            }
        }

        return hasPermission;
    }
}