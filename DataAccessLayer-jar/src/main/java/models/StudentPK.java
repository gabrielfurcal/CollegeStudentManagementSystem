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
    private int userId;

    public StudentPK()
    {
    }

    public StudentPK(String studentId, int userId)
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

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPK studentPK = (StudentPK) o;
        return userId == studentPK.userId &&
                Objects.equals(studentId, studentPK.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, userId);
    }

    @Override
    public String toString()
    {
        return "StudentPK{" + "studentId=" + studentId + ", userId=" + userId + '}';
    }
}