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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "COURSES_SECTIONS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "CourseSection.findAll", query = "SELECT c FROM CourseSection c")
    , @NamedQuery(name = "CourseSection.findByCourseSectionId", query = "SELECT c FROM CourseSection c WHERE c.coursesSectionsPK.courseSectionId = :courseSectionId")
    , @NamedQuery(name = "CourseSection.findByCourseId", query = "SELECT c FROM CourseSection c WHERE c.coursesSectionsPK.courseId = :courseId")
    , @NamedQuery(name = "CourseSection.findByCourseSectionCreationDate", query = "SELECT c FROM CourseSection c WHERE c.courseSectionCreationDate = :courseSectionCreationDate")
})
public class CourseSection implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected CourseSectionPK coursesSectionsPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_SECTION_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date courseSectionCreationDate;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "COURSE_SECTION_DAY")
    private String courseSectionDay;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COURSE_SECTION_START_HOUR")
    private String courseSectionStartHour;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COURSE_SECTION_END_HOUR")
    private String courseSectionEndHour;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_SECTION_ACTIVE")
    private short courseSectionActive;
    
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseSection")
    private List<CourseSectionHistorical> coursesSectionsHistorical;

    public CourseSection()
    {
    }

    public CourseSection(CourseSectionPK coursesSectionsPK)
    {
        this.coursesSectionsPK = coursesSectionsPK;
    }

    public CourseSection(CourseSectionPK coursesSectionsPK, Date courseSectionCreationDate)
    {
        this.coursesSectionsPK = coursesSectionsPK;
        this.courseSectionCreationDate = courseSectionCreationDate;
    }

    public CourseSection(int courseSectionId, String courseId)
    {
        this.coursesSectionsPK = new CourseSectionPK(courseSectionId, courseId);
    }

    public CourseSectionPK getCoursesSectionsPK()
    {
        return coursesSectionsPK;
    }

    public void setCoursesSectionsPK(CourseSectionPK coursesSectionsPK)
    {
        this.coursesSectionsPK = coursesSectionsPK;
    }

    public Date getCourseSectionCreationDate()
    {
        return courseSectionCreationDate;
    }

    public void setCourseSectionCreationDate(Date courseSectionCreationDate)
    {
        this.courseSectionCreationDate = courseSectionCreationDate;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    @XmlTransient
    public List<CourseSectionHistorical> getCoursesSectionsHistorical()
    {
        return coursesSectionsHistorical;
    }

    public void setCoursesSectionsHistorical(List<CourseSectionHistorical> coursesSectionsHistorical)
    {
        this.coursesSectionsHistorical = coursesSectionsHistorical;
    }
    
    public String getCourseSectionDay()
    {
        return courseSectionDay;
    }

    public void setCourseSectionDay(String courseSectionDay)
    {
        this.courseSectionDay = courseSectionDay;
    }

    public String getCourseSectionStartHour()
    {
        return courseSectionStartHour;
    }

    public void setCourseSectionStartHour(String courseSectionStartHour)
    {
        this.courseSectionStartHour = courseSectionStartHour;
    }

    public String getCourseSectionEndHour()
    {
        return courseSectionEndHour;
    }

    public void setCourseSectionEndHour(String courseSectionEndHour)
    {
        this.courseSectionEndHour = courseSectionEndHour;
    }
    
    public short getCourseSectionActive()
    {
        return courseSectionActive;
    }

    public void setCourseSectionActive(short courseSectionActive)
    {
        this.courseSectionActive = courseSectionActive;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (coursesSectionsPK != null ? coursesSectionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseSection))
        {
            return false;
        }
        CourseSection other = (CourseSection) object;
        if ((this.coursesSectionsPK == null && other.coursesSectionsPK != null) || (this.coursesSectionsPK != null && !this.coursesSectionsPK.equals(other.coursesSectionsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "CourseSection{" + "coursesSectionsPK=" + coursesSectionsPK + ", courseSectionCreationDate=" + courseSectionCreationDate + ", courseSectionDay=" + courseSectionDay + ", courseSectionStartHour=" + courseSectionStartHour + ", courseSectionEndHour=" + courseSectionEndHour + ", courseSectionActive=" + courseSectionActive + '}';
    }
}