/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Gabriel_Liberato
 */
@Embeddable
public class CourseSectionPK implements Serializable
{
    @Basic(optional = false)
    @Column(name = "COURSE_SECTION_ID")
    @SequenceGenerator(name="COURSES_SECTIONS_SEQUENCE", sequenceName = "COURSES_SECTIONS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSES_SECTIONS_SEQUENCE")
    private BigDecimal courseSectionId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "COURSE_ID")
    private String courseId;

    public CourseSectionPK()
    {
    }

    public CourseSectionPK(BigDecimal courseSectionId, String courseId)
    {
        this.courseSectionId = courseSectionId;
        this.courseId = courseId;
    }

    public BigDecimal getCourseSectionId()
    {
        return courseSectionId;
    }

    public void setCourseSectionId(BigDecimal courseSectionId)
    {
        this.courseSectionId = courseSectionId;
    }

    public String getCourseId()
    {
        return courseId;
    }

    public void setCourseId(String courseId)
    {
        this.courseId = courseId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (courseSectionId != null ? courseSectionId.hashCode() : 0);
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseSectionPK))
        {
            return false;
        }
        CourseSectionPK other = (CourseSectionPK) object;
        if ((this.courseSectionId == null && other.courseSectionId != null) || (this.courseSectionId != null && !this.courseSectionId.equals(other.courseSectionId)))
        {
            return false;
        }
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "CourseSectionPK{" + "courseSectionId=" + courseSectionId + ", courseId=" + courseId + '}';
    }
}