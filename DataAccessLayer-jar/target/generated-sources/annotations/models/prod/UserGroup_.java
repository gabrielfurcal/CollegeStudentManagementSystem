package models.prod;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Group;
import models.User;
import models.UserGroup;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T11:40:06")
@StaticMetamodel(UserGroup.class)
public class UserGroup_ { 

    public static volatile SingularAttribute<UserGroup, Integer> userGroupId;
    public static volatile SingularAttribute<UserGroup, User> user;
    public static volatile SingularAttribute<UserGroup, Group> group;

}