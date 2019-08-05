package models.prod;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Role;
import models.User;
import models.UserRole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T14:28:09")
@StaticMetamodel(UserRole.class)
public class UserRole_ { 

    public static volatile SingularAttribute<UserRole, Role> role;
    public static volatile SingularAttribute<UserRole, Integer> userRoleId;
    public static volatile SingularAttribute<UserRole, User> user;

}