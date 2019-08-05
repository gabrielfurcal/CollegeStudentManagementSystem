package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.ClassParticipant;
import models.Student;
import models.StudentPK;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T14:28:09")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, Date> studentCreationDate;
    public static volatile SingularAttribute<Student, StudentPK> studentsPK;
    public static volatile SingularAttribute<Student, User> user;
    public static volatile ListAttribute<Student, ClassParticipant> classParticipants;

}