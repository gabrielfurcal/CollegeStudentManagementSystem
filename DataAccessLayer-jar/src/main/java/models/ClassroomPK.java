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
public class ClassroomPK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASSROOM_ID")
    @SequenceGenerator(name="CLASSROOMS_SEQUENCE", sequenceName = "CLASSROOMS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLASSROOMS_SEQUENCE")
    private BigDecimal classroomId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "BUILD_ID")
    private BigDecimal buildId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAMPUS_ID")
    private BigDecimal campusId;

    public ClassroomPK()
    {
    }

    public ClassroomPK(BigDecimal classroomId, BigDecimal buildId, BigDecimal campusId)
    {
        this.classroomId = classroomId;
        this.buildId = buildId;
        this.campusId = campusId;
    }

    public BigDecimal getClassroomId()
    {
        return classroomId;
    }

    public void setClassroomId(BigDecimal classroomId)
    {
        this.classroomId = classroomId;
    }

    public BigDecimal getBuildId()
    {
        return buildId;
    }

    public void setBuildId(BigDecimal buildId)
    {
        this.buildId = buildId;
    }

    public BigDecimal getCampusId()
    {
        return campusId;
    }

    public void setCampusId(BigDecimal campusId)
    {
        this.campusId = campusId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (classroomId != null ? classroomId.hashCode() : 0);
        hash += (buildId != null ? buildId.hashCode() : 0);
        hash += (campusId != null ? campusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassroomPK))
        {
            return false;
        }
        ClassroomPK other = (ClassroomPK) object;
        if ((this.classroomId == null && other.classroomId != null) || (this.classroomId != null && !this.classroomId.equals(other.classroomId)))
        {
            return false;
        }
        if ((this.buildId == null && other.buildId != null) || (this.buildId != null && !this.buildId.equals(other.buildId)))
        {
            return false;
        }
        if ((this.campusId == null && other.campusId != null) || (this.campusId != null && !this.campusId.equals(other.campusId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ClassroomPK{" + "classroomId=" + classroomId + ", buildId=" + buildId + ", campusId=" + campusId + '}';
    }
}