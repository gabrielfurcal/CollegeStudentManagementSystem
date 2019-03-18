/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.ejb.Local;
import models.CourseSectionHistorical;
import models.Student;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface IStudentRepository extends IBaseRepository<Student>
{
    public Student findStudent(String studentId);
    public List<Student> findStudents(CourseSectionHistorical courseSectionHistorical);
}
