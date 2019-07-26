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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "CLASS_PARTICIPANTS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "ClassParticipant.findAll", query = "SELECT c FROM ClassParticipant c")
    , @NamedQuery(name = "ClassParticipant.findByClassParticipantId", query = "SELECT c FROM ClassParticipant c WHERE c.classParticipantId = :classParticipantId")
})
public class ClassParticipant implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASS_PARTICIPANT_ID")
    private int classParticipantId;
    
    @JoinColumn(name = "COURSE_SECT_HIST_ID", referencedColumnName = "COURSE_SECT_HIST_ID")
    @ManyToOne(optional = false)
    private CourseSectionHistorical courseSectHist;
    
    @JoinColumns(
    {
        @JoinColumn(name = "STUDENT_ID", referencedColumnName = "STUDENT_ID")
        , @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    })
    @ManyToOne(optional = false)
    private Student student;

    public ClassParticipant()
    {
    }

    public ClassParticipant(int classParticipantId)
    {
        this.classParticipantId = classParticipantId;
    }

    public int getClassParticipantId()
    {
        return classParticipantId;
    }

    public void setClassParticipantId(int classParticipantId)
    {
        this.classParticipantId = classParticipantId;
    }

    public CourseSectionHistorical getCourseSectHist()
    {
        return courseSectHist;
    }

    public void setCourseSectHist(CourseSectionHistorical courseSectHist)
    {
        this.courseSectHist = courseSectHist;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassParticipant that = (ClassParticipant) o;
        return classParticipantId == that.classParticipantId &&
                Objects.equals(courseSectHist, that.courseSectHist) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classParticipantId, courseSectHist, student);
    }

    @Override
    public String toString()
    {
        return "ClassParticipant{" +
                "classParticipantId=" + classParticipantId +
                ", courseSectHist=" + courseSectHist +
                ", student=" + student +
                '}';
    }
}