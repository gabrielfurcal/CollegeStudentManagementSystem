package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Email;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-26T11:40:06")
@StaticMetamodel(Email.class)
public class Email_ { 

    public static volatile SingularAttribute<Email, Date> emailCreationDate;
    public static volatile SingularAttribute<Email, Integer> emailId;
    public static volatile SingularAttribute<Email, String> emailText;
    public static volatile SingularAttribute<Email, User> user;

}