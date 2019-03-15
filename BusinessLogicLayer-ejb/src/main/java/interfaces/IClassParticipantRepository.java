/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javax.ejb.Local;
import models.ClassParticipant;
import models.CourseSectionHistorical;
import models.Student;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IClassParticipantRepository extends IBaseRepository<ClassParticipant>
{
    public ClassParticipant findClassParticipant(CourseSectionHistorical courseSectionHistorical, Student student);
}
