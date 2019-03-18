/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import models.Telephone;
import models.User;
import models.UserTelephone;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IUserTelephoneRepository extends IBaseRepository<UserTelephone>
{
    public List<UserTelephone> findUsersTelephones(User user);
    public UserTelephone findUserTelephone(User user, Telephone telephone);
    public UserTelephone findUserTelephone(BigDecimal telephoneId);
}
