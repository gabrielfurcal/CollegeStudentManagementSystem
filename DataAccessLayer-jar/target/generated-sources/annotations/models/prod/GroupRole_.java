package models.prod;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Group;
import models.GroupRole;
import models.Role;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T14:28:09")
@StaticMetamodel(GroupRole.class)
public class GroupRole_ { 

    public static volatile SingularAttribute<GroupRole, Role> role;
    public static volatile SingularAttribute<GroupRole, Integer> groupRoleId;
    public static volatile SingularAttribute<GroupRole, Group> group;

}