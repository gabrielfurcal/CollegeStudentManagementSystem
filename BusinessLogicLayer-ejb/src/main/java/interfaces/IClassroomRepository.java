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
import models.Classroom;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IClassroomRepository extends IBaseRepository<Classroom>
{
    public Classroom findClassroom(int classroomId);
    public List<Classroom> findClassrooms(Build build);
}