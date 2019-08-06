package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Course;
import models.CourseSection;
import models.CourseSectionHistorical;
import models.CourseSectionPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-06T10:07:16")
@StaticMetamodel(CourseSection.class)
public class CourseSection_ { 

    public static volatile ListAttribute<CourseSection, CourseSectionHistorical> coursesSectionsHistorical;
    public static volatile SingularAttribute<CourseSection, String> courseSectionEndHour;
    public static volatile SingularAttribute<CourseSection, String> courseSectionStartHour;
    public static volatile SingularAttribute<CourseSection, Course> course;
    public static volatile SingularAttribute<CourseSection, CourseSectionPK> coursesSectionsPK;
    public static volatile SingularAttribute<CourseSection, Short> courseSectionActive;
    public static volatile SingularAttribute<CourseSection, Date> courseSectionCreationDate;
    public static volatile SingularAttribute<CourseSection, String> courseSectionDay;

}