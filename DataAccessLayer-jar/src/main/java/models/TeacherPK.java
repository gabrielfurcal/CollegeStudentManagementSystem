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

/**
 *
 * @author Gabriel_Liberato
 */
@Embeddable
public class TeacherPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="TEACHERS_SEQUENCE", sequenceName = "TEACHERS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEACHERS_SEQUENCE")
    @Column(name = "TEACHER_ID")
    private BigDecimal teacherId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;

    public TeacherPK()
    {
    }

    public TeacherPK(BigDecimal teacherId, BigDecimal userId)
    {
        this.teacherId = teacherId;
        this.userId = userId;
    }

    public BigDecimal getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId(BigDecimal teacherId)
    {
        this.teacherId = teacherId;
    }

    public BigDecimal getUserId()
    {
        return userId;
    }

    public void setUserId(BigDecimal userId)
    {
        this.userId = userId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (teacherId != null ? teacherId.hashCode() : 0);
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeacherPK))
        {
            return false;
        }
        TeacherPK other = (TeacherPK) object;
        if ((this.teacherId == null && other.teacherId != null) || (this.teacherId != null && !this.teacherId.equals(other.teacherId)))
        {
            return false;
        }
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "TeacherPK{" + "teacherId=" + teacherId + ", userId=" + userId + '}';
    }
}