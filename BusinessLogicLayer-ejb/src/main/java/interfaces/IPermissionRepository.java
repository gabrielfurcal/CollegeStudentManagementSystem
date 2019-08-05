/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import models.Permission;
import models.Role;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IPermissionRepository extends IBaseRepository<Permission>
{
    public List<Permission> findPermissions(Role role);
    public boolean hasUserPermission(int userId, String url);
}
