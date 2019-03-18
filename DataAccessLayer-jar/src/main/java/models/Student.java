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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "STUDENTS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findByStudentId", query = "SELECT s FROM Student s WHERE s.studentsPK.studentId = :studentId")
    , @NamedQuery(name = "Student.findByUserId", query = "SELECT s FROM Student s WHERE s.studentsPK.userId = :userId")
, @NamedQuery(name = "Student.findByStudentCreationDate", query = "SELECT s FROM Student s WHERE s.studentCreationDate = :studentCreationDate")
})
public class Student implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected StudentPK studentsPK;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "STUDENT_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date studentCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<ClassParticipant> classParticipants;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Student()
    {
    }

    public Student(StudentPK studentsPK)
    {
        this.studentsPK = studentsPK;
    }

    public Student(StudentPK studentsPK, Date studentCreationDate)
    {
        this.studentsPK = studentsPK;
        this.studentCreationDate = studentCreationDate;
    }

    public Student(String studentId, BigDecimal userId)
    {
        this.studentsPK = new StudentPK(studentId, userId);
    }

    public StudentPK getStudentsPK()
    {
        return studentsPK;
    }

    public void setStudentsPK(StudentPK studentsPK)
    {
        this.studentsPK = studentsPK;
    }

    public Date getStudentCreationDate()
    {
        return studentCreationDate;
    }

    public void setStudentCreationDate(Date studentCreationDate)
    {
        this.studentCreationDate = studentCreationDate;
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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (studentsPK != null ? studentsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student))
        {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentsPK == null && other.studentsPK != null) || (this.studentsPK != null && !this.studentsPK.equals(other.studentsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Student{" + "studentsPK=" + studentsPK + ", studentCreationDate=" + studentCreationDate + ", classParticipants=" + classParticipants + ", user=" + user + '}';
    }
}