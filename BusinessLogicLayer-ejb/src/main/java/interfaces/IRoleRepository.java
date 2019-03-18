/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.ejb.Local;
import models.Group;
import models.Role;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IRoleRepository extends IBaseRepository<Role>
{
    public List<Role> findRoles(Group group);
}
