package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Address;
import models.Build;
import models.Campus;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T11:40:06")
@StaticMetamodel(Campus.class)
public class Campus_ { 

    public static volatile SingularAttribute<Campus, Address> address;
    public static volatile SingularAttribute<Campus, String> campusName;
    public static volatile SingularAttribute<Campus, Integer> campusId;
    public static volatile ListAttribute<Campus, Build> builds;
    public static volatile SingularAttribute<Campus, Date> campusCreationDate;

}