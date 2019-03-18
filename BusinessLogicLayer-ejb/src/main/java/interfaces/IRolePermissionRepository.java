/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.ejb.Local;
import models.Permission;
import models.Role;
import models.RolePermission;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IRolePermissionRepository extends IBaseRepository<RolePermission>
{
    public List<RolePermission> findRolesPermissions(Role role);
    public List<RolePermission> findRolesPermissions(Permission permission);
    public List<RolePermission> findRolesPermissions(Role role, Permission permission);
}
