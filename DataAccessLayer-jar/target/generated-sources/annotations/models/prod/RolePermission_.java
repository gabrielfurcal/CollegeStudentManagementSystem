package models.prod;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Permission;
import models.Role;
import models.RolePermission;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T15:00:36")
@StaticMetamodel(RolePermission.class)
public class RolePermission_ { 

    public static volatile SingularAttribute<RolePermission, Integer> rolePermissionId;
    public static volatile SingularAttribute<RolePermission, Role> role;
    public static volatile SingularAttribute<RolePermission, Permission> permission;

}