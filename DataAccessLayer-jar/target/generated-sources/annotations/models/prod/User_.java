package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Address;
import models.Position;
import models.Student;
import models.User;
import models.UserGroup;
import models.UserRole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T11:40:06")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userUsername;
    public static volatile SingularAttribute<User, String> userPassword;
    public static volatile SingularAttribute<User, Address> address;
    public static volatile ListAttribute<User, UserGroup> usersGroups;
    public static volatile SingularAttribute<User, String> userFatherLastName;
    public static volatile ListAttribute<User, UserRole> usersRoles;
    public static volatile SingularAttribute<User, String> userMotherLastName;
    public static volatile ListAttribute<User, Student> students;
    public static volatile SingularAttribute<User, Date> userCreationDate;
    public static volatile SingularAttribute<User, String> userFirstName;
    public static volatile SingularAttribute<User, Position> position;
    public static volatile SingularAttribute<User, Integer> userId;

}