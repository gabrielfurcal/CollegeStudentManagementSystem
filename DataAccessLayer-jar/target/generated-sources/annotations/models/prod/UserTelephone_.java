package models.prod;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Telephone;
import models.User;
import models.UserTelephone;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-14T11:51:07")
@StaticMetamodel(UserTelephone.class)
public class UserTelephone_ { 

    public static volatile SingularAttribute<UserTelephone, BigDecimal> userTelephoneId;
    public static volatile SingularAttribute<UserTelephone, Telephone> telephone;
    public static volatile SingularAttribute<UserTelephone, User> user;

}