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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_GROUP_ID")
    private int userGroupId;
    
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID")
    @ManyToOne(optional = false)
    private Group group;
    
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    private User user;

    public UserGroup()
    {
    }

    public UserGroup(int userGroupId)
    {
        this.userGroupId = userGroupId;
    }

    public UserGroup(int userGroupId, String userUsername, String groupName)
    {
        this.userGroupId = userGroupId;
    }

    public int getUserGroupId()
    {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return userGroupId == userGroup.userGroupId &&
                Objects.equals(group, userGroup.group) &&
                Objects.equals(user, userGroup.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, group, user);
    }

    @Override
    public String toString()
    {
        return "UserGroup{" + "userGroupId=" + userGroupId + '}';
    }
}