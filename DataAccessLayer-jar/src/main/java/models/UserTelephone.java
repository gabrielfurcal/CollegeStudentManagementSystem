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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel_Liberato
 */
@Entity
@Table(name = "USERS_TELEPHONES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UserTelephone.findAll", query = "SELECT u FROM UserTelephone u")
    , @NamedQuery(name = "UserTelephone.findByUserTelephoneId", query = "SELECT u FROM UserTelephone u WHERE u.userTelephoneId = :userTelephoneId")
})
public class UserTelephone implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="USERS_TELEPHONES_SEQUENCE", sequenceName = "USERS_TELEPHONES_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_TELEPHONES_SEQUENCE")
    @Column(name = "USER_TELEPHONE_ID")
    private BigDecimal userTelephoneId;
    
    @JoinColumn(name = "TELEPHONE_ID", referencedColumnName = "TELEPHONE_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Telephone telephone;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public UserTelephone()
    {
    }

    public UserTelephone(BigDecimal userTelephoneId)
    {
        this.userTelephoneId = userTelephoneId;
    }

    public BigDecimal getUserTelephoneId()
    {
        return userTelephoneId;
    }

    public void setUserTelephoneId(BigDecimal userTelephoneId)
    {
        this.userTelephoneId = userTelephoneId;
    }

    public Telephone getTelephone()
    {
        return telephone;
    }

    public void setTelephone(Telephone telephone)
    {
        this.telephone = telephone;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userTelephoneId != null ? userTelephoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTelephone))
        {
            return false;
        }
        UserTelephone other = (UserTelephone) object;
        if ((this.userTelephoneId == null && other.userTelephoneId != null) || (this.userTelephoneId != null && !this.userTelephoneId.equals(other.userTelephoneId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "UserTelephone{" + "userTelephoneId=" + userTelephoneId + ", telephone=" + telephone + ", user=" + user + '}';
    }
}