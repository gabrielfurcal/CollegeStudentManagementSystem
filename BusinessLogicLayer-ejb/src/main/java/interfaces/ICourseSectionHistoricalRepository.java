/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import javax.ejb.Local;
import models.CourseSection;
import models.CourseSectionHistorical;

/**
 *
 * @author Gabriel_Liberato
 */
@Local
public interface ICourseSectionHistoricalRepository extends IBaseRepository<CourseSectionHistorical>
{
    public List<CourseSectionHistorical> findCoursesSectionsHistorical(CourseSection courseSection);
}
