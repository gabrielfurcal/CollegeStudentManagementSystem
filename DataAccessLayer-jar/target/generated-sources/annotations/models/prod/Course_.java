package models.prod;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Course;
import models.CourseSection;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T09:31:49")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, String> courseName;
    public static volatile SingularAttribute<Course, BigDecimal> coursePrice;
    public static volatile SingularAttribute<Course, Short> courseActive;
    public static volatile ListAttribute<Course, CourseSection> coursesSections;
    public static volatile SingularAttribute<Course, Short> courseAmountHours;
    public static volatile SingularAttribute<Course, Date> courseCreationDate;
    public static volatile SingularAttribute<Course, String> courseId;

}