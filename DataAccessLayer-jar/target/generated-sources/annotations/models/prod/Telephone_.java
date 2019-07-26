package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Telephone;
import models.TelephoneType;
import models.UserTelephone;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T11:40:06")
@StaticMetamodel(Telephone.class)
public class Telephone_ { 

    public static volatile SingularAttribute<Telephone, String> telephoneNumber;
    public static volatile SingularAttribute<Telephone, Date> telephoneCreationDate;
    public static volatile ListAttribute<Telephone, UserTelephone> usersTelephones;
    public static volatile SingularAttribute<Telephone, TelephoneType> telephoneType;
    public static volatile SingularAttribute<Telephone, Integer> telephoneId;

}