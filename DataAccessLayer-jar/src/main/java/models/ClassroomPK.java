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
public class ClassroomPK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASSROOM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classroomId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "BUILD_ID")
    private int buildId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAMPUS_ID")
    private int campusId;

    public ClassroomPK()
    {
    }

    public ClassroomPK(int classroomId, int buildId, int campusId)
    {
        this.classroomId = classroomId;
        this.buildId = buildId;
        this.campusId = campusId;
    }

    public int getClassroomId()
    {
        return classroomId;
    }

    public void setClassroomId(int classroomId)
    {
        this.classroomId = classroomId;
    }

    public int getBuildId()
    {
        return buildId;
    }

    public void setBuildId(int buildId)
    {
        this.buildId = buildId;
    }

    public int getCampusId()
    {
        return campusId;
    }

    public void setCampusId(int campusId)
    {
        this.campusId = campusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomPK that = (ClassroomPK) o;
        return classroomId == that.classroomId &&
                buildId == that.buildId &&
                campusId == that.campusId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomId, buildId, campusId);
    }

    @Override
    public String toString()
    {
        return "ClassroomPK{" + "classroomId=" + classroomId + ", buildId=" + buildId + ", campusId=" + campusId + '}';
    }
}