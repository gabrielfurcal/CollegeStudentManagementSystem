package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.GroupRole;
import models.Role;
import models.RolePermission;
import models.UserRole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T14:56:23")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile ListAttribute<Role, RolePermission> rolesPermissions;
    public static volatile SingularAttribute<Role, Date> roleCreationDate;
    public static volatile ListAttribute<Role, GroupRole> groupsRoles;
    public static volatile ListAttribute<Role, UserRole> usersRoles;
    public static volatile SingularAttribute<Role, Integer> roleId;
    public static volatile SingularAttribute<Role, String> roleName;
    public static volatile SingularAttribute<Role, String> roleDescription;

}