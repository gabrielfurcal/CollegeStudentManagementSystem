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
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "CAMPUS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Campus.findAll", query = "SELECT c FROM Campus c")
    , @NamedQuery(name = "Campus.findByCampusId", query = "SELECT c FROM Campus c WHERE c.campusId = :campusId")
    , @NamedQuery(name = "Campus.findByCampusName", query = "SELECT c FROM Campus c WHERE c.campusName = :campusName")
    , @NamedQuery(name = "Campus.findByCampusCreationDate", query = "SELECT c FROM Campus c WHERE c.campusCreationDate = :campusCreationDate")
})
public class Campus implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAMPUS_ID")
    private int campusId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CAMPUS_NAME")
    private String campusName;
    
    @Column(name = "CAMPUS_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date campusCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campus")
    private List<Build> builds;
    
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address address;

    public Campus()
    {
    }

    public Campus(int campusId)
    {
        this.campusId = campusId;
    }

    public Campus(int campusId, String campusName)
    {
        this.campusId = campusId;
        this.campusName = campusName;
    }

    public int getCampusId()
    {
        return campusId;
    }

    public void setCampusId(int campusId)
    {
        this.campusId = campusId;
    }

    public String getCampusName()
    {
        return campusName;
    }

    public void setCampusName(String campusName)
    {
        this.campusName = campusName.toUpperCase();
    }

    public Date getCampusCreationDate()
    {
        return campusCreationDate;
    }

    public void setCampusCreationDate(Date campusCreationDate)
    {
        this.campusCreationDate = campusCreationDate;
    }

    @XmlTransient
    public List<Build> getBuilds()
    {
        return builds;
    }

    public void setBuilds(List<Build> builds)
    {
        this.builds = builds;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campus campus = (Campus) o;
        return campusId == campus.campusId &&
                Objects.equals(campusName, campus.campusName) &&
                Objects.equals(campusCreationDate, campus.campusCreationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campusId, campusName, campusCreationDate);
    }

    @Override
    public String toString()
    {
        return "Campus{" + "campusId=" + campusId + ", campusName=" + campusName + ", campusCreationDate=" + campusCreationDate + '}';
    }
}