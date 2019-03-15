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
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "TELEPHONES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Telephone.findAll", query = "SELECT t FROM Telephone t")
    , @NamedQuery(name = "Telephone.findByTelephoneId", query = "SELECT t FROM Telephone t WHERE t.telephoneId = :telephoneId")
    , @NamedQuery(name = "Telephone.findByTelephoneNumber", query = "SELECT t FROM Telephone t WHERE t.telephoneNumber = :telephoneNumber")
    , @NamedQuery(name = "Telephone.findByTelephoneCreationDate", query = "SELECT t FROM Telephone t WHERE t.telephoneCreationDate = :telephoneCreationDate")
})
public class Telephone implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="TELEPHONES_SEQUENCE", sequenceName = "TELEPHONES_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TELEPHONES_SEQUENCE")
    @Column(name = "TELEPHONE_ID")
    private BigDecimal telephoneId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TELEPHONE_NUMBER", unique = true)
    private String telephoneNumber;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TELEPHONE_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date telephoneCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telephone")
    private List<UserTelephone> usersTelephones;
    
    @JoinColumn(name = "TELEPHONE_TYPE_ID", referencedColumnName = "TELEPHONE_TYPE_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TelephoneType telephoneType;

    public Telephone()
    {
    }

    public Telephone(BigDecimal telephoneId)
    {
        this.telephoneId = telephoneId;
    }

    public Telephone(BigDecimal telephoneId, String telephoneNumber, Date telephoneCreationDate)
    {
        this.telephoneId = telephoneId;
        this.telephoneNumber = telephoneNumber;
        this.telephoneCreationDate = telephoneCreationDate;
    }

    public BigDecimal getTelephoneId()
    {
        return telephoneId;
    }

    public void setTelephoneId(BigDecimal telephoneId)
    {
        this.telephoneId = telephoneId;
    }

    public String getTelephoneNumber()
    {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    public Date getTelephoneCreationDate()
    {
        return telephoneCreationDate;
    }

    public void setTelephoneCreationDate(Date telephoneCreationDate)
    {
        this.telephoneCreationDate = telephoneCreationDate;
    }

    @XmlTransient
    public List<UserTelephone> getUsersTelephones()
    {
        return usersTelephones;
    }

    public void setUsersTelephones(List<UserTelephone> usersTelephones)
    {
        this.usersTelephones = usersTelephones;
    }

    public TelephoneType getTelephoneType()
    {
        return telephoneType;
    }

    public void setTelephoneType(TelephoneType telephoneType)
    {
        this.telephoneType = telephoneType;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (telephoneId != null ? telephoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telephone))
        {
            return false;
        }
        Telephone other = (Telephone) object;
        if ((this.telephoneId == null && other.telephoneId != null) || (this.telephoneId != null && !this.telephoneId.equals(other.telephoneId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Telephone{" + "telephoneId=" + telephoneId + ", telephoneNumber=" + telephoneNumber + ", telephoneCreationDate=" + telephoneCreationDate + ", usersTelephones=" + usersTelephones + ", telephoneType=" + telephoneType + '}';
    }
}