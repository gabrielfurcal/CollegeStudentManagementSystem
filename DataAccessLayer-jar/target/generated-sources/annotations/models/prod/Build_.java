package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Build;
import models.BuildPK;
import models.Campus;
import models.Classroom;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-14T11:51:07")
@StaticMetamodel(Build.class)
public class Build_ { 

    public static volatile SingularAttribute<Build, String> buildName;
    public static volatile SingularAttribute<Build, Campus> campus;
    public static volatile SingularAttribute<Build, Date> buildCreationDate;
    public static volatile SingularAttribute<Build, BuildPK> buildsPK;
    public static volatile ListAttribute<Build, Classroom> classrooms;

}