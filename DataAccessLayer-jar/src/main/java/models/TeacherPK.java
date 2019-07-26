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

/**
 *
 * @author Gabriel_Liberato
 */
@Embeddable
public class TeacherPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEACHER_ID")
    private int teacherId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private int userId;

    public TeacherPK()
    {
    }

    public TeacherPK(int teacherId, int userId)
    {
        this.teacherId = teacherId;
        this.userId = userId;
    }

    public int getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId(int teacherId)
    {
        this.teacherId = teacherId;
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
        TeacherPK teacherPK = (TeacherPK) o;
        return teacherId == teacherPK.teacherId &&
                userId == teacherPK.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, userId);
    }

    @Override
    public String toString()
    {
        return "TeacherPK{" + "teacherId=" + teacherId + ", userId=" + userId + '}';
    }
}