/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.ejb.Local;
import models.User;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IUserRepository extends IBaseRepository<User>
{
    public User findUser(String username);
    public User findUser(String username, String password);
}
