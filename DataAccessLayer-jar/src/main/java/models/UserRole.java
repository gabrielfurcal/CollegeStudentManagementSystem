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
@Table(name = "USERS_ROLES")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u")
    , @NamedQuery(name = "UserRole.findByUserRoleId", query = "SELECT u FROM UserRole u WHERE u.userRoleId = :userRoleId")
})
public class UserRole implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;
    
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne(optional = false)
    private Role role;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User user;

    public UserRole()
    {
    }

    public UserRole(int userRoleId)
    {
        this.userRoleId = userRoleId;
    }

    public UserRole(int userRoleId, String userUsername, String roleName)
    {
        this.userRoleId = userRoleId;
    }

    public int getUserRoleId()
    {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId)
    {
        this.userRoleId = userRoleId;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
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
        UserRole userRole = (UserRole) o;
        return userRoleId == userRole.userRoleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId);
    }

    @Override
    public String toString()
    {
        return "UserRole{" + "userRoleId=" + userRoleId + '}';
    }
}