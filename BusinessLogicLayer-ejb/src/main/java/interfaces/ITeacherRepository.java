/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.math.BigDecimal;
import javax.ejb.Local;
import models.Teacher;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface ITeacherRepository extends IBaseRepository<Teacher>
{
    public Teacher findTeacher(BigDecimal teacherId);
}
