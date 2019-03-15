/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "COURSES_SECTIONS_HISTORICAL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "CourseSectionHistorical.findAll", query = "SELECT c FROM CourseSectionHistorical c")
    , @NamedQuery(name = "CourseSectionHistorical.findByCourseSectHistId", query = "SELECT c FROM CourseSectionHistorical c WHERE c.courseSectHistId = :courseSectHistId")
    , @NamedQuery(name = "CourseSectionHistorical.findByCourseSectHistQuota", query = "SELECT c FROM CourseSectionHistorical c WHERE c.courseSectHistQuota = :courseSectHistQuota")
    , @NamedQuery(name = "CourseSectionHistorical.findByCourseSectHistPrice", query = "SELECT c FROM CourseSectionHistorical c WHERE c.courseSectHistPrice = :courseSectHistPrice")
    , @NamedQuery(name = "CourseSectionHistorical.findByCourseSectHistCreationDate", query = "SELECT c FROM CourseSectionHistorical c WHERE c.courseSectHistCreationDate = :courseSectHistCreationDate")
})
public class CourseSectionHistorical implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="COURSES_SECT_HIST_SEQUENCE", sequenceName = "COURSES_SECT_HIST_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSES_SECT_HIST_SEQUENCE")
    @Column(name = "COURSE_SECT_HIST_ID")
    private BigDecimal courseSectHistId;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_SECT_HIST_PRICE")
    private BigDecimal courseSectHistPrice;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_SECT_HIST_QUOTA")
    private BigDecimal courseSectHistQuota;
    
    @Size(max = 3)
    @Column(name = "COURSE_SECT_HIST_DAY")
    private String courseSectHistDay;
    
    @Size(max = 10)
    @Column(name = "COURSE_SECT_HIST_START_HOUR")
    private String courseSectHistStartHour;
    
    @Size(max = 10)
    @Column(name = "COURSE_SECT_HIST_END_HOUR")
    private String courseSectHistEndHour;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_SECT_HIST_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date courseSectHistCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseSectHist")
    private List<ClassParticipant> classParticipants;
    
    @JoinColumns(
    {
        @JoinColumn(name = "CLASSROOM_ID", referencedColumnName = "CLASSROOM_ID")
        , @JoinColumn(name = "BUILD_ID", referencedColumnName = "BUILD_ID")
        , @JoinColumn(name = "CAMPUS_ID", referencedColumnName = "CAMPUS_ID")
    })
    @ManyToOne
    private Classroom classroom;
    
    @JoinColumns(
    {
        @JoinColumn(name = "COURSE_SECTION_ID", referencedColumnName = "COURSE_SECTION_ID")
        , @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
    })
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private CourseSection courseSection;
    
    @JoinColumns(
    {
        @JoinColumn(name = "PERIOD_YEAR", referencedColumnName = "PERIOD_YEAR")
        , @JoinColumn(name = "PERIOD_QUARTER", referencedColumnName = "PERIOD_QUARTER")
    })
    @ManyToOne(optional = false)
    private Period period;
    
    @JoinColumns(
    {
        @JoinColumn(name = "TEACHER_ID", referencedColumnName = "TEACHER_ID")
        , @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    })
    @ManyToOne
    private Teacher teacher;

    public CourseSectionHistorical()
    {
    }

    public CourseSectionHistorical(BigDecimal courseSectHistId)
    {
        this.courseSectHistId = courseSectHistId;
    }

    public CourseSectionHistorical(BigDecimal courseSectHistId, BigDecimal courseSectHistQuota, BigDecimal courseSectHistPrice, Date courseSectHistCreationDate)
    {
        this.courseSectHistId = courseSectHistId;
        this.courseSectHistQuota = courseSectHistQuota;
        this.courseSectHistPrice = courseSectHistPrice;
        this.courseSectHistCreationDate = courseSectHistCreationDate;
    }

    public BigDecimal getCourseSectHistId()
    {
        return courseSectHistId;
    }

    public void setCourseSectHistId(BigDecimal courseSectHistId)
    {
        this.courseSectHistId = courseSectHistId;
    }

    public BigDecimal getCourseSectHistQuota()
    {
        return courseSectHistQuota;
    }

    public void setCourseSectHistQuota(BigDecimal courseSectHistQuota)
    {
        this.courseSectHistQuota = courseSectHistQuota;
    }

    public BigDecimal getCourseSectHistPrice()
    {
        return courseSectHistPrice;
    }

    public void setCourseSectHistPrice(BigDecimal courseSectHistPrice)
    {
        this.courseSectHistPrice = courseSectHistPrice;
    }

    public String getCourseSectHistDay()
    {
        return courseSectHistDay;
    }

    public void setCourseSectHistDay(String courseSectHistDay)
    {
        this.courseSectHistDay = courseSectHistDay;
    }

    public String getCourseSectHistStartHour()
    {
        return courseSectHistStartHour;
    }

    public void setCourseSectHistStartHour(String courseSectHistStartHour)
    {
        this.courseSectHistStartHour = courseSectHistStartHour;
    }

    public String getCourseSectHistEndHour()
    {
        return courseSectHistEndHour;
    }

    public void setCourseSectHistEndHour(String courseSectHistEndHour)
    {
        this.courseSectHistEndHour = courseSectHistEndHour;
    }
    
    public Date getCourseSectHistCreationDate()
    {
        return courseSectHistCreationDate;
    }

    public void setCourseSectHistCreationDate(Date courseSectHistCreationDate)
    {
        this.courseSectHistCreationDate = courseSectHistCreationDate;
    }

    @XmlTransient
    public List<ClassParticipant> getClassParticipants()
    {
        return classParticipants;
    }

    public void setClassParticipants(List<ClassParticipant> classParticipants)
    {
        this.classParticipants = classParticipants;
    }

    public Classroom getClassroom()
    {
        return classroom;
    }

    public void setClassroom(Classroom classroom)
    {
        this.classroom = classroom;
    }

    public CourseSection getCourseSection()
    {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection)
    {
        this.courseSection = courseSection;
    }

    public Period getPeriod()
    {
        return period;
    }

    public void setPeriod(Period period)
    {
        this.period = period;
    }

    public Teacher getTeacher()
    {
        return teacher;
    }

    public void setTeacher(Teacher teacher)
    {
        this.teacher = teacher;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (courseSectHistId != null ? courseSectHistId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseSectionHistorical))
        {
            return false;
        }
        CourseSectionHistorical other = (CourseSectionHistorical) object;
        if ((this.courseSectHistId == null && other.courseSectHistId != null) || (this.courseSectHistId != null && !this.courseSectHistId.equals(other.courseSectHistId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "CoursesSectionsHistorical{" + "courseSectHistId=" + courseSectHistId + ", courseSectHistQuota=" + courseSectHistQuota + ", courseSectHistPrice=" + courseSectHistPrice + ", courseSectHistCreationDate=" + courseSectHistCreationDate + ", classParticipants=" + classParticipants + ", classroom=" + classroom + ", courseSection=" + courseSection + ", period=" + period + ", teacher=" + teacher + '}';
    }
}