/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.ejb.Local;
import models.Course;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface ICourseRepository extends IBaseRepository<Course>
{
    
}
