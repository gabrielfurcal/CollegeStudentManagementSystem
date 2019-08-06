package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Address;
import models.Campus;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-06T10:07:16")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> addressProvince;
    public static volatile SingularAttribute<Address, String> addressStreetAndNumber;
    public static volatile SingularAttribute<Address, String> addressSector;
    public static volatile SingularAttribute<Address, String> addressLongitude;
    public static volatile SingularAttribute<Address, Date> addressCreationDate;
    public static volatile ListAttribute<Address, Campus> campus;
    public static volatile SingularAttribute<Address, String> addressNeighborhood;
    public static volatile SingularAttribute<Address, String> addressMunicipality;
    public static volatile SingularAttribute<Address, String> addressLatitude;
    public static volatile ListAttribute<Address, User> users;
    public static volatile SingularAttribute<Address, Integer> addressId;

}