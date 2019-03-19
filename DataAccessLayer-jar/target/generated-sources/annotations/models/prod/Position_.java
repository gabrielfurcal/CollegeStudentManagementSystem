package models.prod;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Position;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T09:31:49")
@StaticMetamodel(Position.class)
public class Position_ { 

    public static volatile SingularAttribute<Position, String> positionName;
    public static volatile SingularAttribute<Position, BigDecimal> positionId;
    public static volatile SingularAttribute<Position, String> positionDescription;
    public static volatile SingularAttribute<Position, Date> positionCreationDate;
    public static volatile ListAttribute<Position, User> users;

}