package models.prod;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Permission;
import models.RolePermission;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-19T09:31:49")
@StaticMetamodel(Permission.class)
public class Permission_ { 

    public static volatile SingularAttribute<Permission, BigDecimal> permissionId;
    public static volatile ListAttribute<Permission, RolePermission> rolesPermissions;
    public static volatile SingularAttribute<Permission, String> permissionUrl;
    public static volatile SingularAttribute<Permission, Date> permissionCreationDate;
    public static volatile SingularAttribute<Permission, String> permissionDescription;
    public static volatile SingularAttribute<Permission, String> permissionName;

}