package models.prod;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.ClassParticipant;
import models.CourseSectionHistorical;
import models.Student;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T14:28:09")
@StaticMetamodel(ClassParticipant.class)
public class ClassParticipant_ { 

    public static volatile SingularAttribute<ClassParticipant, Student> student;
    public static volatile SingularAttribute<ClassParticipant, CourseSectionHistorical> courseSectHist;
    public static volatile SingularAttribute<ClassParticipant, Integer> classParticipantId;

}