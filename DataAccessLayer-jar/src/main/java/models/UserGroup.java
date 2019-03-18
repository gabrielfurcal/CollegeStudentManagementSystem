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
@Table(name = "USERS_GROUPS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u")
    , @NamedQuery(name = "UserGroup.findByUserGroupId", query = "SELECT u FROM UserGroup u WHERE u.userGroupId = :userGroupId")
})
public class UserGroup implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name="USERS_GROUPS_SEQUENCE", sequenceName = "USERS_GROUPS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_GROUPS_SEQUENCE")
    @Column(name = "USER_GROUP_ID")
    private BigDecimal userGroupId;
    
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    @ManyToOne(optional = false)
    private Group group;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User user;

    public UserGroup()
    {
    }

    public UserGroup(BigDecimal userGroupId)
    {
        this.userGroupId = userGroupId;
    }

    public UserGroup(BigDecimal userGroupId, String userUsername, String groupName)
    {
        this.userGroupId = userGroupId;
    }

    public BigDecimal getUserGroupId()
    {
        return userGroupId;
    }

    public void setUserGroupId(BigDecimal userGroupId)
    {
        this.userGroupId = userGroupId;
    }
    
    public Group getGroup()
    {
        return group;
    }

    public void setGroup(Group group)
    {
        this.group = group;
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
        hash += (userGroupId != null ? userGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroup))
        {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.userGroupId == null && other.userGroupId != null) || (this.userGroupId != null && !this.userGroupId.equals(other.userGroupId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "UserGroup{" + "userGroupId=" + userGroupId + ", group=" + group + ", user=" + user + '}';
    }
}