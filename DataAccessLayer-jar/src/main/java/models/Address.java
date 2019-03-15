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
@Table(name = "ADDRESSES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
    , @NamedQuery(name = "Address.findByAddressId", query = "SELECT a FROM Address a WHERE a.addressId = :addressId")
    , @NamedQuery(name = "Address.findByAddressStreetAndNumber", query = "SELECT a FROM Address a WHERE a.addressStreetAndNumber = :addressStreetAndNumber")
    , @NamedQuery(name = "Address.findByAddressNeighborhood", query = "SELECT a FROM Address a WHERE a.addressNeighborhood = :addressNeighborhood")
    , @NamedQuery(name = "Address.findByAddressSector", query = "SELECT a FROM Address a WHERE a.addressSector = :addressSector")
    , @NamedQuery(name = "Address.findByAddressMunicipality", query = "SELECT a FROM Address a WHERE a.addressMunicipality = :addressMunicipality")
    , @NamedQuery(name = "Address.findByAddressProvince", query = "SELECT a FROM Address a WHERE a.addressProvince = :addressProvince")
    , @NamedQuery(name = "Address.findByAddressLatitude", query = "SELECT a FROM Address a WHERE a.addressLatitude = :addressLatitude")
    , @NamedQuery(name = "Address.findByAddressLongitude", query = "SELECT a FROM Address a WHERE a.addressLongitude = :addressLongitude")
    , @NamedQuery(name = "Address.findByAddressCreationDate", query = "SELECT a FROM Address a WHERE a.addressCreationDate = :addressCreationDate")
})
public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;
   
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Column(name = "ADDRESS_ID")
    @SequenceGenerator(name="ADDRESSES_SEQUENCE", sequenceName = "ADDRESSES_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESSES_SEQUENCE")
    private BigDecimal addressId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ADDRESS_STREET_AND_NUMBER")
    private String addressStreetAndNumber;
    
    @Size(max = 255)
    @Column(name = "ADDRESS_NEIGHBORHOOD")
    private String addressNeighborhood;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ADDRESS_SECTOR")
    private String addressSector;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ADDRESS_MUNICIPALITY")
    private String addressMunicipality;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ADDRESS_PROVINCE")
    private String addressProvince;
    
    @Size(max = 30)
    @Column(name = "ADDRESS_LATITUDE")
    private String addressLatitude;
    
    @Size(max = 30)
    @Column(name = "ADDRESS_LONGITUDE")
    private String addressLongitude;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ADDRESS_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addressCreationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<Campus> campus;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address")
    private List<User> users;
    

    public Address()
    {
    }

    public Address(BigDecimal addressId)
    {
        this.addressId = addressId;
    }

    public Address(BigDecimal addressId, String addressStreetAndNumber, String addressSector, String addressMunicipality, String addressProvince, Date addressCreationDate)
    {
        this.addressId = addressId;
        this.addressStreetAndNumber = addressStreetAndNumber;
        this.addressSector = addressSector;
        this.addressMunicipality = addressMunicipality;
        this.addressProvince = addressProvince;
        this.addressCreationDate = addressCreationDate;
    }

    public BigDecimal getAddressId()
    {
        return addressId;
    }

    public void setAddressId(BigDecimal addressId)
    {
        this.addressId = addressId;
    }

    public String getAddressStreetAndNumber()
    {
        return addressStreetAndNumber;
    }

    public void setAddressStreetAndNumber(String addressStreetAndNumber)
    {
        this.addressStreetAndNumber = addressStreetAndNumber;
    }

    public String getAddressNeighborhood()
    {
        return addressNeighborhood;
    }

    public void setAddressNeighborhood(String addressNeighborhood)
    {
        this.addressNeighborhood = addressNeighborhood;
    }

    public String getAddressSector()
    {
        return addressSector;
    }

    public void setAddressSector(String addressSector)
    {
        this.addressSector = addressSector;
    }

    public String getAddressMunicipality()
    {
        return addressMunicipality;
    }

    public void setAddressMunicipality(String addressMunicipality)
    {
        this.addressMunicipality = addressMunicipality;
    }

    public String getAddressProvince()
    {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince)
    {
        this.addressProvince = addressProvince;
    }

    public String getAddressLatitude()
    {
        return addressLatitude;
    }

    public void setAddressLatitude(String addressLatitude)
    {
        this.addressLatitude = addressLatitude;
    }

    public String getAddressLongitude()
    {
        return addressLongitude;
    }

    public void setAddressLongitude(String addressLongitude)
    {
        this.addressLongitude = addressLongitude;
    }

    public Date getAddressCreationDate()
    {
        return addressCreationDate;
    }

    public void setAddressCreationDate(Date addressCreationDate)
    {
        this.addressCreationDate = addressCreationDate;
    }

    @XmlTransient
    public List<Campus> getCampus()
    {
        return campus;
    }

    public void setCampus(List<Campus> campus)
    {
        this.campus = campus;
    }

    @XmlTransient
    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address))
        {
            return false;
        }
        Address other = (Address) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Address{" + "addressId=" + addressId + ", addressStreetAndNumber=" + addressStreetAndNumber + ", addressNeighborhood=" + addressNeighborhood + ", addressSector=" + addressSector + ", addressMunicipality=" + addressMunicipality + ", addressProvince=" + addressProvince + ", addressLatitude=" + addressLatitude + ", addressLongitude=" + addressLongitude + ", addressCreationDate=" + addressCreationDate + ", campus=" + campus + ", users=" + users + '}';
    }
}