package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Build;
import models.Classroom;
import models.ClassroomPK;
import models.CourseSectionHistorical;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T11:40:06")
@StaticMetamodel(Classroom.class)
public class Classroom_ { 

    public static volatile ListAttribute<Classroom, CourseSectionHistorical> coursesSectionsHistorical;
    public static volatile SingularAttribute<Classroom, Build> build;
    public static volatile SingularAttribute<Classroom, String> classroomName;
    public static volatile SingularAttribute<Classroom, ClassroomPK> classroomsPK;
    public static volatile SingularAttribute<Classroom, Date> classroomCreationDate;

}