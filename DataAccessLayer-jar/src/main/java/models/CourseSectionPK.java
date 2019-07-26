/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseSectionId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "COURSE_ID")
    private String courseId;

    public CourseSectionPK()
    {
    }

    public CourseSectionPK(int courseSectionId, String courseId)
    {
        this.courseSectionId = courseSectionId;
        this.courseId = courseId;
    }

    public int getCourseSectionId()
    {
        return courseSectionId;
    }

    public void setCourseSectionId(int courseSectionId)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseSectionPK that = (CourseSectionPK) o;
        return courseSectionId == that.courseSectionId &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseSectionId, courseId);
    }

    @Override
    public String toString()
    {
        return "CourseSectionPK{" + "courseSectionId=" + courseSectionId + ", courseId=" + courseId + '}';
    }
}