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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TELEPHONE_ID")
    private int telephoneId;
    
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

    public Telephone(int telephoneId)
    {
        this.telephoneId = telephoneId;
    }

    public Telephone(int telephoneId, String telephoneNumber, Date telephoneCreationDate)
    {
        this.telephoneId = telephoneId;
        this.telephoneNumber = telephoneNumber;
        this.telephoneCreationDate = telephoneCreationDate;
    }

    public int getTelephoneId()
    {
        return telephoneId;
    }

    public void setTelephoneId(int telephoneId)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return telephoneId == telephone.telephoneId &&
                Objects.equals(telephoneNumber, telephone.telephoneNumber) &&
                Objects.equals(telephoneCreationDate, telephone.telephoneCreationDate) &&
                Objects.equals(usersTelephones, telephone.usersTelephones) &&
                Objects.equals(telephoneType, telephone.telephoneType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telephoneId, telephoneNumber, telephoneCreationDate, usersTelephones, telephoneType);
    }

    @Override
    public String toString()
    {
        return "Telephone{" + "telephoneId=" + telephoneId + ", telephoneNumber=" + telephoneNumber + ", telephoneCreationDate=" + telephoneCreationDate + '}';
    }
}