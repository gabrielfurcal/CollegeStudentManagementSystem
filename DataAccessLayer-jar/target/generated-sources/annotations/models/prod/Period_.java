package models.prod;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.CourseSectionHistorical;
import models.Period;
import models.PeriodPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-14T11:51:07")
@StaticMetamodel(Period.class)
public class Period_ { 

    public static volatile ListAttribute<Period, CourseSectionHistorical> coursesSectionsHistorical;
    public static volatile SingularAttribute<Period, PeriodPK> periodsPK;
    public static volatile SingularAttribute<Period, Date> periodCreationDate;

}