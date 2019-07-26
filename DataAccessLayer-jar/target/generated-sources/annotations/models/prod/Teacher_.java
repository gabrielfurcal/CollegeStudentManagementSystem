package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.CourseSectionHistorical;
import models.Teacher;
import models.TeacherPK;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T11:40:06")
@StaticMetamodel(Teacher.class)
public class Teacher_ { 

    public static volatile ListAttribute<Teacher, CourseSectionHistorical> coursesSectionsHistorical;
    public static volatile SingularAttribute<Teacher, TeacherPK> teachersPK;
    public static volatile SingularAttribute<Teacher, User> user;
    public static volatile SingularAttribute<Teacher, Date> teacherCreationDate;

}