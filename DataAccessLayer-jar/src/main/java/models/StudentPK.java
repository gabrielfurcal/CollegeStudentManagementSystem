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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Gabriel_Liberato
 */
@Embeddable
public class StudentPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "STUDENT_ID")
    private String studentId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private BigDecimal userId;

    public StudentPK()
    {
    }

    public StudentPK(String studentId, BigDecimal userId)
    {
        this.studentId = studentId;
        this.userId = userId;
    }

    public String getStudentId()
    {
        return studentId;
    }

    public void setStudentId(String studentId)
    {
        this.studentId = studentId;
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
        hash += (studentId != null ? studentId.hashCode() : 0);
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentPK))
        {
            return false;
        }
        StudentPK other = (StudentPK) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId)))
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
        return "StudentPK{" + "studentId=" + studentId + ", userId=" + userId + '}';
    }
}