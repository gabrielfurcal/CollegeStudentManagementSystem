package models.prod;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Group;
import models.User;
import models.UserGroup;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-14T11:51:07")
@StaticMetamodel(UserGroup.class)
public class UserGroup_ { 

    public static volatile SingularAttribute<UserGroup, BigDecimal> userGroupId;
    public static volatile SingularAttribute<UserGroup, User> user;
    public static volatile SingularAttribute<UserGroup, Group> group;

}