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
@Table(name = "GROUPS")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Group.findAll", query = "SELECT g FROM Group g")
    , @NamedQuery(name = "Group.findByGroupId", query = "SELECT g FROM Group g WHERE g.groupId = :groupId")
    , @NamedQuery(name = "Group.findByGroupName", query = "SELECT g FROM Group g WHERE g.groupName = :groupName")
    , @NamedQuery(name = "Group.findByGroupCreationDate", query = "SELECT g FROM Group g WHERE g.groupCreationDate = :groupCreationDate")
    , @NamedQuery(name = "Group.findByGroupDescription", query = "SELECT g FROM Group g WHERE g.groupDescription = :groupDescription")
})
public class Group implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "GROUP_ID")
    @SequenceGenerator(name="GROUPS_SEQUENCE", sequenceName = "GROUPS_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUPS_SEQUENCE")
    private BigDecimal groupId;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "GROUP_NAME")
    private String groupName;
    
    @Column(name = "GROUP_CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date groupCreationDate;
    
    @Size(min = 1, max = 200)
    @Column(name = "GROUP_DESCRIPTION")
    private String groupDescription;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<UserGroup> usersGroups;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<GroupRole> groupsRoles;

    public Group()
    {
    }

    public Group(BigDecimal groupId)
    {
        this.groupId = groupId;
    }

    public Group(BigDecimal groupId, String groupName)
    {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public BigDecimal getGroupId()
    {
        return groupId;
    }

    public void setGroupId(BigDecimal groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName.toUpperCase();
    }

    public Date getGroupCreationDate()
    {
        return groupCreationDate;
    }

    public void setGroupCreationDate(Date groupCreationDate)
    {
        this.groupCreationDate = groupCreationDate;
    }

    public String getGroupDescription()
    {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription)
    {
        this.groupDescription = groupDescription;
    }

    @XmlTransient
    public List<UserGroup> getUsersGroups()
    {
        return usersGroups;
    }

    public void setUsersGroups(List<UserGroup> usersGroups)
    {
        this.usersGroups = usersGroups;
    }

    @XmlTransient
    public List<GroupRole> getGroupsRoles()
    {
        return groupsRoles;
    }

    public void setGroupsRoles(List<GroupRole> groupsRoles)
    {
        this.groupsRoles = groupsRoles;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Group))
        {
            return false;
        }
        Group other = (Group) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Group{" + "groupId=" + groupId + ", groupName=" + groupName + ", groupCreationDate=" + groupCreationDate + ", groupDescription=" + groupDescription + ", usersGroups=" + usersGroups + ", groupsRoles=" + groupsRoles + '}';
    }
}
