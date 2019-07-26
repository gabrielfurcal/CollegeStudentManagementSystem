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
import javax.persistence.Id;
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
@Table(name = "COURSES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
    , @NamedQuery(name = "Course.findByCourseId", query = "SELECT c FROM Course c WHERE c.courseId = :courseId")
    , @NamedQuery(name = "Course.findByCourseName", query = "SELECT c FROM Course c WHERE c.courseName = :courseName")
    , @NamedQuery(name = "Course.findByCoursePrice", query = "SELECT c FROM Course c WHERE c.coursePrice = :coursePrice")
    , @NamedQuery(name = "Course.findByCourseCreationDate", query = "SELECT c FROM Course c WHERE c.courseCreationDate = :courseCreationDate")
})
public class Course implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "COURSE_ID")
    private String courseId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COURSE_NAME")
    private String courseName;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_PRICE")
    private double coursePrice;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date courseCreationDate;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "COURSE_ACTIVE")
    private short courseActive;
    
    @Column(name = "COURSE_AMOUNT_HOURS")
    private short courseAmountHours;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<CourseSection> coursesSections;

    public Course()
    {
    }

    public Course(String courseId)
    {
        this.courseId = courseId;
    }

    public Course(String courseId, String courseName, double coursePrice, Date courseCreationDate)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.courseCreationDate = courseCreationDate;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId.toUpperCase();
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName.toUpperCase();
    }

    public double getCoursePrice()
    {
        return coursePrice;
    }

    public void setCoursePrice(double coursePrice)
    {
        this.coursePrice = coursePrice;
    }

    public Date getCourseCreationDate()
    {
        return courseCreationDate;
    }

    public void setCourseCreationDate(Date courseCreationDate)
    {
        this.courseCreationDate = courseCreationDate;
    }

    public short getCourseActive()
    {
        return courseActive;
    }

    public void setCourseActive(short courseActive)
    {
        this.courseActive = courseActive;
    }
    
    public Short getCourseAmountHours()
    {
        return courseAmountHours;
    }

    public void setCourseAmountHours(Short courseAmountHours)
    {
        this.courseAmountHours = courseAmountHours;
    }

    @XmlTransient
    public List<CourseSection> getCoursesSections()
    {
        return coursesSections;
    }
    
    public void setCoursesSections(List<CourseSection> coursesSections)
    {
        this.coursesSections = coursesSections;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course))
        {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Course{" + "courseId=" + courseId + ", courseName=" + courseName + ", coursePrice=" + coursePrice + ", courseCreationDate=" + courseCreationDate + ", courseActive=" + courseActive + ", courseAmountHours=" + courseAmountHours + ", coursesSections=" + coursesSections + '}';
    }
}