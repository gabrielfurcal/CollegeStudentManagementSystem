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
    @SequenceGenerator(name="CLASS_PARTICIPANTS_SEQUENCE", sequenceName = "CLASS_PARTICIPANTS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASS_PARTICIPANTS_SEQUENCE")
    @Column(name = "CLASS_PARTICIPANT_ID")
    private BigDecimal classParticipantId;
    
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

    public ClassParticipant(BigDecimal classParticipantId)
    {
        this.classParticipantId = classParticipantId;
    }

    public BigDecimal getClassParticipantId()
    {
        return classParticipantId;
    }

    public void setClassParticipantId(BigDecimal classParticipantId)
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
    public int hashCode()
    {
        int hash = 0;
        hash += (classParticipantId != null ? classParticipantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassParticipant))
        {
            return false;
        }
        ClassParticipant other = (ClassParticipant) object;
        if ((this.classParticipantId == null && other.classParticipantId != null) || (this.classParticipantId != null && !this.classParticipantId.equals(other.classParticipantId)))
        {
            return false;
        }
        return true;
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