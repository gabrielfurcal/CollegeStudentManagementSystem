/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import models.Build;
import models.Campus;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IBuildRepository extends IBaseRepository<Build>
{
    public List<Build> findBuilds(Campus campus);
    public Build findBuild(BigDecimal buildId);
}