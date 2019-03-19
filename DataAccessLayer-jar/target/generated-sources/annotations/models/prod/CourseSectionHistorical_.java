package models.prod;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.ClassParticipant;
import models.Classroom;
import models.CourseSection;
import models.CourseSectionHistorical;
import models.Period;
import models.Teacher;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T09:31:49")
@StaticMetamodel(CourseSectionHistorical.class)
public class CourseSectionHistorical_ { 

    public static volatile SingularAttribute<CourseSectionHistorical, BigDecimal> courseSectHistId;
    public static volatile SingularAttribute<CourseSectionHistorical, CourseSection> courseSection;
    public static volatile SingularAttribute<CourseSectionHistorical, Period> period;
    public static volatile SingularAttribute<CourseSectionHistorical, Teacher> teacher;
    public static volatile SingularAttribute<CourseSectionHistorical, BigDecimal> courseSectHistPrice;
    public static volatile SingularAttribute<CourseSectionHistorical, BigDecimal> courseSectHistQuota;
    public static volatile SingularAttribute<CourseSectionHistorical, Date> courseSectHistCreationDate;
    public static volatile SingularAttribute<CourseSectionHistorical, String> courseSectHistStartHour;
    public static volatile SingularAttribute<CourseSectionHistorical, String> courseSectHistDay;
    public static volatile SingularAttribute<CourseSectionHistorical, Classroom> classroom;
    public static volatile ListAttribute<CourseSectionHistorical, ClassParticipant> classParticipants;
    public static volatile SingularAttribute<CourseSectionHistorical, String> courseSectHistEndHour;

}