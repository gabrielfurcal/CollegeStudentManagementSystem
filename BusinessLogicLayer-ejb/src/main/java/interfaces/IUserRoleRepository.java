/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.ejb.Local;
import models.Role;
import models.User;
import models.UserRole;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IUserRoleRepository extends IBaseRepository<UserRole>
{
    public List<UserRole> findUsersRoles(User user);
    public List<UserRole> findUsersRoles(User user, Role role);
}
