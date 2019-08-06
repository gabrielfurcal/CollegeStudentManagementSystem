package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Telephone;
import models.TelephoneType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-06T10:07:16")
@StaticMetamodel(TelephoneType.class)
public class TelephoneType_ { 

    public static volatile ListAttribute<TelephoneType, Telephone> telephones;
    public static volatile SingularAttribute<TelephoneType, Integer> telephoneTypeId;
    public static volatile SingularAttribute<TelephoneType, String> telephoneTypeName;
    public static volatile SingularAttribute<TelephoneType, Date> telephoneTypeCreationDate;

}