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

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface ITelephoneRepository extends IBaseRepository<Telephone>
{
    public List<Telephone> findTelephones(String telephoneNumber);
    public Telephone findTelephone(int id, String telephoneNumber);
}
