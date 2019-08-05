package models.prod;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Telephone;
import models.User;
import models.UserTelephone;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-05T14:28:09")
@StaticMetamodel(UserTelephone.class)
public class UserTelephone_ { 

    public static volatile SingularAttribute<UserTelephone, Integer> userTelephoneId;
    public static volatile SingularAttribute<UserTelephone, Telephone> telephone;
    public static volatile SingularAttribute<UserTelephone, User> user;

}