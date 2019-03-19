package models.prod;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Group;
import models.GroupRole;
import models.UserGroup;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T09:31:49")
@StaticMetamodel(Group.class)
public class Group_ { 

    public static volatile SingularAttribute<Group, Date> groupCreationDate;
    public static volatile SingularAttribute<Group, String> groupName;
    public static volatile SingularAttribute<Group, String> groupDescription;
    public static volatile ListAttribute<Group, UserGroup> usersGroups;
    public static volatile ListAttribute<Group, GroupRole> groupsRoles;
    public static volatile SingularAttribute<Group, BigDecimal> groupId;

}