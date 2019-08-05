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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "TEACHERS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Teacher.findAll", query = "SELECT t FROM Teacher t")
    , @NamedQuery(name = "Teacher.findByTeacherId", query = "SELECT t FROM Teacher t WHERE t.teachersPK.teacherId = :teacherId")
    , @NamedQuery(name = "Teacher.findByUserId", query = "SELECT t FROM Teacher t WHERE t.teachersPK.userId = :userId")
    , @NamedQuery(name = "Teacher.findByTeacherCreationDate", query = "SELECT t FROM Teacher t WHERE t.teacherCreationDate = :teacherCreationDate")
})
public class Teacher implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected TeacherPK teachersPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEACHER_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date teacherCreationDate;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
    
    @OneToMany(mappedBy = "teacher")
    private List<CourseSectionHistorical> coursesSectionsHistorical;

    public Teacher()
    {
    }

    public Teacher(TeacherPK teachersPK)
    {
        this.teachersPK = teachersPK;
    }

    public Teacher(TeacherPK teachersPK, Date teacherCreationDate)
    {
        this.teachersPK = teachersPK;
        this.teacherCreationDate = teacherCreationDate;
    }

    public Teacher(int teacherId, int userId)
    {
        this.teachersPK = new TeacherPK(teacherId, userId);
    }

    public TeacherPK getTeachersPK()
    {
        return teachersPK;
    }

    public void setTeachersPK(TeacherPK teachersPK)
    {
        this.teachersPK = teachersPK;
    }

    public Date getTeacherCreationDate()
    {
        return teacherCreationDate;
    }

    public void setTeacherCreationDate(Date teacherCreationDate)
    {
        this.teacherCreationDate = teacherCreationDate;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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
        hash += (teachersPK != null ? teachersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teacher))
        {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.teachersPK == null && other.teachersPK != null) || (this.teachersPK != null && !this.teachersPK.equals(other.teachersPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Teacher{" + "teachersPK=" + teachersPK + ", teacherCreationDate=" + teacherCreationDate + '}';
    } 
}