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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "CLASSROOMS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Classroom.findAll", query = "SELECT c FROM Classroom c")
    , @NamedQuery(name = "Classroom.findByClassroomId", query = "SELECT c FROM Classroom c WHERE c.classroomsPK.classroomId = :classroomId")
    , @NamedQuery(name = "Classroom.findByBuildId", query = "SELECT c FROM Classroom c WHERE c.classroomsPK.buildId = :buildId")
    , @NamedQuery(name = "Classroom.findByCampusId", query = "SELECT c FROM Classroom c WHERE c.classroomsPK.campusId = :campusId")
    , @NamedQuery(name = "Classroom.findByClassroomName", query = "SELECT c FROM Classroom c WHERE c.classroomName = :classroomName")
    , @NamedQuery(name = "Classroom.findByClassroomCreationDate", query = "SELECT c FROM Classroom c WHERE c.classroomCreationDate = :classroomCreationDate")
})
public class Classroom implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ClassroomPK classroomsPK;
    
    @Size(max = 255)
    @Column(name = "CLASSROOM_NAME")
    private String classroomName;
    
    @Column(name = "CLASSROOM_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date classroomCreationDate;
    
    @JoinColumns(
    {
        @JoinColumn(name = "BUILD_ID", referencedColumnName = "BUILD_ID", insertable = false, updatable = false)
        , @JoinColumn(name = "CAMPUS_ID", referencedColumnName = "CAMPUS_ID", insertable = false, updatable = false)
    })
    @ManyToOne(optional = false)
    private Build build;
    
    @OneToMany(mappedBy = "classroom")
    private List<CourseSectionHistorical> coursesSectionsHistorical;

    public Classroom()
    {
    }

    public Classroom(ClassroomPK classroomsPK)
    {
        this.classroomsPK = classroomsPK;
    }

    public Classroom(int classroomId, int buildId, int campusId)
    {
        this.classroomsPK = new ClassroomPK(classroomId, buildId, campusId);
    }

    public ClassroomPK getClassroomsPK()
    {
        return classroomsPK;
    }

    public void setClassroomsPK(ClassroomPK classroomsPK)
    {
        this.classroomsPK = classroomsPK;
    }

    public String getClassroomName()
    {
        return classroomName;
    }

    public void setClassroomName(String classroomName)
    {
        this.classroomName = classroomName;
    }

    public Date getClassroomCreationDate()
    {
        return classroomCreationDate;
    }

    public void setClassroomCreationDate(Date classroomCreationDate)
    {
        this.classroomCreationDate = classroomCreationDate;
    }

    public Build getBuild()
    {
        return build;
    }

    public void setBuild(Build build)
    {
        this.build = build;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (classroomsPK != null ? classroomsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classroom))
        {
            return false;
        }
        Classroom other = (Classroom) object;
        if ((this.classroomsPK == null && other.classroomsPK != null) || (this.classroomsPK != null && !this.classroomsPK.equals(other.classroomsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Classroom{" + "classroomsPK=" + classroomsPK + ", classroomName=" + classroomName + ", classroomCreationDate=" + classroomCreationDate + '}';
    }
}