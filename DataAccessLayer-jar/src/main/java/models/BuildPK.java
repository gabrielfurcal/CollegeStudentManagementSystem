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
public class BuildPK implements Serializable
{
    @Basic(optional = false)
    @NotNull
    @Column(name = "BUILD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buildId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAMPUS_ID")
    private int campusId;

    public BuildPK()
    {
    }

    public BuildPK(int buildId, int campusId)
    {
        this.buildId = buildId;
        this.campusId = campusId;
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
        BuildPK buildPK = (BuildPK) o;
        return buildId == buildPK.buildId &&
                campusId == buildPK.campusId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildId, campusId);
    }

    @Override
    public String toString()
    {
        return "BuildPK{" + "buildId=" + buildId + ", campusId=" + campusId + '}';
    }
}