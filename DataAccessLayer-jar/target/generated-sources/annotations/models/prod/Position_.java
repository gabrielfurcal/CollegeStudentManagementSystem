package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Position;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T14:28:09")
@StaticMetamodel(Position.class)
public class Position_ { 

    public static volatile SingularAttribute<Position, String> positionName;
    public static volatile SingularAttribute<Position, Integer> positionId;
    public static volatile SingularAttribute<Position, String> positionDescription;
    public static volatile SingularAttribute<Position, Date> positionCreationDate;
    public static volatile ListAttribute<Position, User> users;

}