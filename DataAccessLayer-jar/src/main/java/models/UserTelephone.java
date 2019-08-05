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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_TELEPHONE_ID")
    private int userTelephoneId;
    
    @JoinColumn(name = "TELEPHONE_ID", referencedColumnName = "TELEPHONE_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Telephone telephone;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User user;

    public UserTelephone()
    {
    }

    public UserTelephone(int userTelephoneId)
    {
        this.userTelephoneId = userTelephoneId;
    }

    public int getUserTelephoneId()
    {
        return userTelephoneId;
    }

    public void setUserTelephoneId(int userTelephoneId)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTelephone that = (UserTelephone) o;
        return userTelephoneId == that.userTelephoneId &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userTelephoneId, telephone, user);
    }

    @Override
    public String toString()
    {
        return "UserTelephone{" + "userTelephoneId=" + userTelephoneId + '}';
    }
}