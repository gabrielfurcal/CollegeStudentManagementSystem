/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.ejb.Local;
import models.Email;
import models.User;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IEmailRepository extends IBaseRepository<Email>
{
    public List<Email> findEmails(User user);
}
