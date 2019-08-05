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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "BUILDS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Build.findAll", query = "SELECT b FROM Build b")
    , @NamedQuery(name = "Build.findByBuildId", query = "SELECT b FROM Build b WHERE b.buildsPK.buildId = :buildId")
    , @NamedQuery(name = "Build.findByCampusId", query = "SELECT b FROM Build b WHERE b.buildsPK.campusId = :campusId")
    , @NamedQuery(name = "Build.findByBuildName", query = "SELECT b FROM Build b WHERE b.buildName = :buildName")
    , @NamedQuery(name = "Build.findByBuildCreationDate", query = "SELECT b FROM Build b WHERE b.buildCreationDate = :buildCreationDate")
})
public class Build implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected BuildPK buildsPK;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "BUILD_NAME")
    private String buildName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "BUILD_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date buildCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "build")
    private List<Classroom> classrooms;
    
    @JoinColumn(name = "CAMPUS_ID", referencedColumnName = "CAMPUS_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Campus campus;

    public Build()
    {
    }

    public Build(BuildPK buildsPK)
    {
        this.buildsPK = buildsPK;
    }

    public Build(BuildPK buildsPK, String buildName, Date buildCreationDate)
    {
        this.buildsPK = buildsPK;
        this.buildName = buildName;
        this.buildCreationDate = buildCreationDate;
    }

    public Build(int buildId, int campusId)
    {
        this.buildsPK = new BuildPK(buildId, campusId);
    }

    public BuildPK getBuildsPK()
    {
        return buildsPK;
    }

    public void setBuildsPK(BuildPK buildsPK)
    {
        this.buildsPK = buildsPK;
    }

    public String getBuildName()
    {
        return buildName;
    }

    public void setBuildName(String buildName)
    {
        this.buildName = buildName;
    }

    public Date getBuildCreationDate()
    {
        return buildCreationDate;
    }

    public void setBuildCreationDate(Date buildCreationDate)
    {
        this.buildCreationDate = buildCreationDate;
    }

    @XmlTransient
    public List<Classroom> getClassrooms()
    {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms)
    {
        this.classrooms = classrooms;
    }

    public Campus getCampus()
    {
        return campus;
    }

    public void setCampus(Campus campus)
    {
        this.campus = campus;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (buildsPK != null ? buildsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Build))
        {
            return false;
        }
        Build other = (Build) object;
        if ((this.buildsPK == null && other.buildsPK != null) || (this.buildsPK != null && !this.buildsPK.equals(other.buildsPK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Build{" + "buildsPK=" + buildsPK + ", buildName=" + buildName + ", buildCreationDate=" + buildCreationDate + '}';
    }
}