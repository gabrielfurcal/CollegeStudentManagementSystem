/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.ejb.Local;
import models.Group;
import models.GroupRole;
import models.Role;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IGroupRoleRepository extends IBaseRepository<GroupRole>
{
    public List<GroupRole> findGroupsRoles(Group group);
    public List<GroupRole> findGroupsRoles(Role role);
    public List<GroupRole> findGroupsRoles(Group group, Role role);
}
