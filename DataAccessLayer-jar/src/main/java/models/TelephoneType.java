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
@Table(name = "TELEPHONES_TYPES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "TelephoneType.findAll", query = "SELECT t FROM TelephoneType t")
    , @NamedQuery(name = "TelephoneType.findByTelephoneTypeId", query = "SELECT t FROM TelephoneType t WHERE t.telephoneTypeId = :telephoneTypeId")
    , @NamedQuery(name = "TelephoneType.findByTelephoneTypeName", query = "SELECT t FROM TelephoneType t WHERE t.telephoneTypeName = :telephoneTypeName")
    , @NamedQuery(name = "TelephoneType.findByTelephoneTypeCreationDate", query = "SELECT t FROM TelephoneType t WHERE t.telephoneTypeCreationDate = :telephoneTypeCreationDate")
})
public class TelephoneType implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TELEPHONE_TYPE_ID")
    private int telephoneTypeId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TELEPHONE_TYPE_NAME")
    private String telephoneTypeName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TELEPHONE_TYPE_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date telephoneTypeCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telephoneType")
    private List<Telephone> telephones;

    public TelephoneType()
    {
    }

    public TelephoneType(int telephoneTypeId)
    {
        this.telephoneTypeId = telephoneTypeId;
    }

    public TelephoneType(int telephoneTypeId, String telephoneTypeName, Date telephoneTypeCreationDate)
    {
        this.telephoneTypeId = telephoneTypeId;
        this.telephoneTypeName = telephoneTypeName;
        this.telephoneTypeCreationDate = telephoneTypeCreationDate;
    }

    public int getTelephoneTypeId()
    {
        return telephoneTypeId;
    }

    public void setTelephoneTypeId(int telephoneTypeId)
    {
        this.telephoneTypeId = telephoneTypeId;
    }

    public String getTelephoneTypeName()
    {
        return telephoneTypeName;
    }

    public void setTelephoneTypeName(String telephoneTypeName)
    {
        this.telephoneTypeName = telephoneTypeName;
    }

    public Date getTelephoneTypeCreationDate()
    {
        return telephoneTypeCreationDate;
    }

    public void setTelephoneTypeCreationDate(Date telephoneTypeCreationDate)
    {
        this.telephoneTypeCreationDate = telephoneTypeCreationDate;
    }

    @XmlTransient
    public List<Telephone> getTelephones()
    {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones)
    {
        this.telephones = telephones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelephoneType that = (TelephoneType) o;
        return telephoneTypeId == that.telephoneTypeId &&
                Objects.equals(telephoneTypeName, that.telephoneTypeName) &&
                Objects.equals(telephoneTypeCreationDate, that.telephoneTypeCreationDate) &&
                Objects.equals(telephones, that.telephones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephoneTypeId, telephoneTypeName, telephoneTypeCreationDate, telephones);
    }

    @Override
    public String toString()
    {
        return "TelephoneType{" + "telephoneTypeId=" + telephoneTypeId + ", telephoneTypeName=" + telephoneTypeName + ", telephoneTypeCreationDate=" + telephoneTypeCreationDate + '}';
    }
}