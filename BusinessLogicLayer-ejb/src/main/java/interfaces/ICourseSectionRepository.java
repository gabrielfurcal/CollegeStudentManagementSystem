/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;
import models.Course;
import models.CourseSection;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface ICourseSectionRepository extends IBaseRepository<CourseSection>
{
    public List<CourseSection> findCoursesSections(Course course);
    public CourseSection findCourseSection(int courseSectionId);
}
